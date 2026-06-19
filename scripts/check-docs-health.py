#!/usr/bin/env python3
"""Read-only documentation health checks for Nota-Toolkit-Android."""

from __future__ import annotations

import argparse
import json
import re
import sys
from dataclasses import dataclass
from pathlib import Path
from urllib.parse import unquote

REPO_ROOT = Path(__file__).resolve().parents[1]
REQUIRED_FRONTMATTER = ("title", "type", "status", "phase", "updated", "summary")
REQUIRED_ENTRIES = (
    "docs/README.md",
    "docs/INDEX.md",
    "docs/DOCUMENTATION.md",
    "docs/DOC-SPEC.md",
    "dev/README.md",
    "dev/progress/status.md",
)
FRONTMATTER_EXEMPT = {"AGENTS.md", "CLAUDE.md"}
SKIP_DIR_PARTS = {
    "archive",
    ".git",
    ".gradle",
    "build",
    "node_modules",
}
ROOT_LINK_PREFIXES = (
    "AGENTS.md",
    "CLAUDE.md",
    "README.md",
    "docs/",
    "dev/",
    "gradle/",
    "scripts/",
)
LINK_SKIP = ("http://", "https://", "mailto:", "tel:", "file:", "data:", "#")


@dataclass(frozen=True)
class Finding:
    path: str
    message: str


def parse_args() -> argparse.Namespace:
    p = argparse.ArgumentParser(description="Nota-Toolkit docs health check (read-only).")
    p.add_argument("--strict-frontmatter", action="store_true")
    p.add_argument("--strict-links", action="store_true")
    p.add_argument("--max-details", type=int, default=30)
    p.add_argument("--format", choices=("text", "json"), default="text")
    return p.parse_args()


def repo_rel(path: Path) -> str:
    return path.relative_to(REPO_ROOT).as_posix()


def iter_markdown() -> list[Path]:
    files: list[Path] = []
    for path in REPO_ROOT.rglob("*.md"):
        parts = path.relative_to(REPO_ROOT).parts
        if any(p in SKIP_DIR_PARTS for p in parts):
            continue
        if parts[0] not in {"docs", "dev"} and repo_rel(path) not in FRONTMATTER_EXEMPT:
            continue
        if path.is_file():
            files.append(path)
    for name in FRONTMATTER_EXEMPT:
        p = REPO_ROOT / name
        if p.is_file():
            files.append(p)
    return sorted(set(files))


def check_required() -> list[Finding]:
    return [
        Finding(e, "required entry missing")
        for e in REQUIRED_ENTRIES
        if not (REPO_ROOT / e).exists()
    ]


def parse_frontmatter(text: str) -> list[str]:
    lines = text.splitlines()
    if not lines or lines[0].strip() != "---":
        return ["missing YAML frontmatter"]
    try:
        end = lines[1:].index("---") + 1
    except ValueError:
        return ["frontmatter not closed"]
    body = "\n".join(lines[1:end])
    missing = [f for f in REQUIRED_FRONTMATTER if not re.search(rf"^{f}\s*:", body, re.M)]
    return [f"missing fields: {', '.join(missing)}"] if missing else []


def frontmatter_findings(files: list[Path]) -> list[Finding]:
    out: list[Finding] = []
    for path in files:
        if repo_rel(path) in FRONTMATTER_EXEMPT:
            continue
        text = path.read_text(encoding="utf-8", errors="replace")
        for msg in parse_frontmatter(text):
            out.append(Finding(repo_rel(path), msg))
    return out


def strip_code(text: str) -> str:
    text = re.sub(r"```.*?```", "", text, flags=re.DOTALL)
    return re.sub(r"`[^`\n]*`", "", text)


def link_targets(text: str) -> list[str]:
    targets: list[str] = []
    for m in re.finditer(r"\[[^\]]*\]\(([^)]+)\)", text):
        targets.append(m.group(1))
    return targets


def resolve_link(source: Path, raw: str) -> Path | None:
    t = unquote(raw.strip().strip("<>"))
    if not t or t.startswith(LINK_SKIP):
        return None
    t = re.sub(r":\d+(?::\d+)?$", "", t.split("#", 1)[0])
    if not t:
        return None
    if t.startswith(ROOT_LINK_PREFIXES):
        candidate = (REPO_ROOT / t).resolve()
        if candidate.exists():
            return candidate
    return (source.parent / t).resolve()


def link_findings(files: list[Path]) -> list[Finding]:
    out: list[Finding] = []
    for path in files:
        if "templates" in path.parts:
            continue
        text = strip_code(path.read_text(encoding="utf-8", errors="replace"))
        for raw in link_targets(text):
            resolved = resolve_link(path, raw)
            if resolved and not resolved.exists():
                out.append(Finding(repo_rel(path), f"broken link: {raw}"))
    return out


def main() -> int:
    args = parse_args()
    files = iter_markdown()
    errors: dict[str, list[Finding]] = {}
    warnings: dict[str, list[Finding]] = {}

    req = check_required()
    if req:
        errors["required_entries"] = req

    fm = frontmatter_findings(files)
    links = link_findings(files)
    if args.strict_frontmatter:
        errors["frontmatter"] = fm
    elif fm:
        warnings["frontmatter"] = fm
    if args.strict_links:
        errors["links"] = links
    elif links:
        warnings["links"] = links

    err_n = sum(len(v) for v in errors.values())
    warn_n = sum(len(v) for v in warnings.values())
    result = "failed" if err_n else ("passed_with_warnings" if warn_n else "passed")

    if args.format == "json":
        print(
            json.dumps(
                {
                    "result": result,
                    "markdown_files_checked": len(files),
                    "counts": {"errors": err_n, "warnings": warn_n},
                    "errors": {k: [{"path": f.path, "message": f.message} for f in v] for k, v in errors.items()},
                    "warnings": {k: [{"path": f.path, "message": f.message} for f in v] for k, v in warnings.items()},
                },
                ensure_ascii=False,
                indent=2,
            )
        )
    else:
        print("Docs health check (Nota-Toolkit-Android)")
        print(f"- files: {len(files)} | errors: {err_n} | warnings: {warn_n}")
        for label, groups in [("Errors", errors), ("Warnings", warnings)]:
            for name, items in groups.items():
                print(f"  {label}/{name}: {len(items)}")
                for f in items[: args.max_details]:
                    print(f"    - {f.path}: {f.message}")
        print(f"Result: {result}")

    return 1 if err_n else 0


if __name__ == "__main__":
    sys.exit(main())

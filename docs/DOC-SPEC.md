---
title: "文档系统规范"
type: concept
status: accepted
phase: N/A
updated: 2026-06-19
summary: "文档分类、命名、索引与健康检查"
---

# Nota-Toolkit 文档系统规范

**原则**：文档即代码、单一事实源、模块源码为 API 唯一来源、`docs/` 与 `dev/` 分离。

## 分类

| type | 位置 |
|------|------|
| `architecture` | `docs/architecture/`、`docs/architecture/modules/` |
| `decision` | `docs/decisions/` |
| `concept` | `docs/`、`docs/architecture/concepts/` |
| `reference` | `docs/reference/` |
| `guide` | `docs/guides/` |
| `roadmap` | `dev/roadmap/active/`、`archive/` |
| `plan` | `dev/plans/` |
| `progress` | `dev/progress/` |
| `index` | `dev/*/INDEX.md`、`docs/INDEX.md` |
| `verification` | `dev/verification/` |

`AGENTS.md`、`CLAUDE.md` 豁免 frontmatter。

## 命名

| 类型 | 格式 | 示例 |
|------|------|------|
| ADR | `NNN-topic.md` | `001-nota-package-consolidation.md` |
| Phase | `phaseN_descriptor.md` | `active/phase1_doc-system-bootstrap.md` |
| 模块架构 | `<module>.md` | `modules/android-common.md` |
| 跨模块概念 | `topic.md` | `concepts/adapter-framework.md` |
| 指南 | `topic.md` | `guides/getting-started.md` |

## 索引与健康检查

```bash
./scripts/generate-docs-index.sh       # 更新 docs/INDEX.md（禁止手改）
python3 scripts/check-docs-health.py   # frontmatter、链接、必备入口
```

结构变更后两者均须运行。模板见 `docs/templates/`。

## 交叉引用

文档末尾附「相关文档」；术语引用 [glossary.md](glossary.md)；使用相对路径。

---
title: "包名命名约定"
type: concept
status: accepted
phase: N/A
updated: 2026-06-19
summary: "nota.* 与 tiiehenry.* 双包名体系及迁移规则"
---

# 包名命名约定

## 双包名体系

| 包根 | 状态 | 用途 |
|------|------|------|
| `nota.*` | **当前标准** | 新代码、Active 模块主包 |
| `tiiehenry.*` | **Legacy** | 历史模块，仅 bugfix |

## 规则

1. **新类**一律放在 `nota.*` 下，按功能分子包（`nota.android.*`, `nota.crypto.*`, `nota.io.*`）
2. **Gradle namespace** 与主 Kotlin/Java 包对齐（已知例外见 module-governance）
3. **Maven groupId** 统一为 `tiiehenry.nota.toolkit`（历史原因，与 Java 包无关）
4. **不删除** Legacy 包中的公开类；标记 `@Deprecated` 并指向 `nota.*` 替代

## 迁移检查清单

- [ ] 新 API 是否在 `nota.*` 下？
- [ ] namespace 是否与包根一致？
- [ ] 是否与 Legacy 类重复？若重复，文档说明选型
- [ ] ADR 是否记录破坏性变更？

## 相关文档

- [ADR-001](../../decisions/001-nota-package-consolidation.md)
- [module-governance.md](../module-governance.md)
- [glossary.md](../../glossary.md)

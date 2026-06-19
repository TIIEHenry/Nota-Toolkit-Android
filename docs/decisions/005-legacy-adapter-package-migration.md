---
title: "ADR-005: Legacy Adapter 包迁移"
type: decision
status: accepted
phase: 6
updated: 2026-06-19
summary: "android-base / android-fragment 从 tiiehenry.android.* 迁至 nota.android.base.* / nota.android.fragment.*"
---

# ADR-005: Legacy Adapter 包迁移

## 背景

Phase 2 已修正 Legacy 模块 `namespace`，但 Java 包仍为 `tiiehenry.android.*`，与 ADR-002 不一致。消费方与文档无法从 namespace 推断 import 路径。

## 决策

### 包映射

| 模块 | 旧包 | 新包 |
|------|------|------|
| android-base | `tiiehenry.android.view.*` | `nota.android.base.view.*` |
| android-base | `tiiehenry.android.text.*` | `nota.android.base.text.*` |
| android-fragment | `tiiehenry.android.fragment.*` | `nota.android.fragment.*` |

### 版本

- `android-base`：**1.2 → 2.0**（major，import 全变）
- `android-fragment`：**1.1 → 2.0**（major，依赖 base 2.0）

### 模块状态

- 重新纳入 `settings.gradle`，与 Active 模块同等构建验证
- 仍标记 **Legacy**：新功能不写入，仅维护；新项目用 `android-common` VB Adapter

### SimpleTextWatcher

`nota.android.base.text.SimpleTextWatcher` 保留并 `@Deprecated`，指向 `nota.android.text.SimpleTextWatcher`（android-common）。

## 后果

- 已有依赖 `tiiehenry.android.*` 的项目须改 import 并升 major 版本
- namespace 与 Java 包根对齐（`nota.android.base` / `nota.android.fragment`）

## 相关

- [ADR-001](001-nota-package-consolidation.md)
- [ADR-002](002-namespace-java-package-alignment.md)
- [legacy-adapters.md](../architecture/modules/legacy-adapters.md)

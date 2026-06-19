---
title: "Phase 6: Legacy Adapter 包迁移"
type: roadmap
status: archived
phase: 6
updated: 2026-06-19
summary: "android-base/fragment tiiehenry → nota 包迁移，纳入构建，v2.0"
---

# Phase 6: Legacy Adapter 包迁移

## 目标

- [x] ADR-005 Legacy Adapter 包迁移策略
- [x] `android-base`：`tiiehenry.android.*` → `nota.android.base.*`，version **2.0**
- [x] `android-fragment`：`tiiehenry.android.fragment.*` → `nota.android.fragment.*`，version **2.0**
- [x] 纳入 `settings.gradle`，`assembleRelease` 9 模块通过
- [x] 修复 `IAdapter.getData` / `getDataCount` 默认方法（构建暴露的既有缺陷）
- [x] 移除 `android-base` 冗余 `kotlin-android` 插件声明
- [x] `docs/guides/legacy-package-migration.md`
- [x] 更新 legacy-adapters、module-governance、AGENTS.md
- [x] Phase 6 归档

## 验收标准

- [x] `./gradlew assembleRelease` 含 base + fragment
- [x] namespace 与 Java 包根一致（base / fragment）
- [x] `check-docs-health.py` 无 error

## 相关文档

- [ADR-005](../../../docs/decisions/005-legacy-adapter-package-migration.md)
- [legacy-package-migration.md](../../../docs/guides/legacy-package-migration.md)

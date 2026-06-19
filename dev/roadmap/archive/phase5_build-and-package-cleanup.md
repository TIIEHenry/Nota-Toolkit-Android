---
title: "Phase 5: 构建验证与包名收尾"
type: roadmap
status: archived
phase: 5
updated: 2026-06-19
summary: "gradlew、全模块构建、LoadingView 包对齐、Adapter 指南"
---

# Phase 5: 构建验证与包名收尾

## 目标

- [x] 补全 `gradlew` / `gradlew.bat` wrapper 脚本
- [x] 修复 `android-widget-flowtag` night 模式缺失 color 资源
- [x] `./gradlew assembleRelease` 全 Active 模块构建通过
- [x] `scripts/verify-build.sh` 构建验证脚本
- [x] `LoadingView` 迁至 `nota.android.widget.loadingview`，version 1.1
- [x] ADR-004 LoadingView 包对齐
- [x] `docs/guides/recyclerview-viewbinding.md`
- [x] 更新 widget-loadingview 指南与 module 文档
- [x] Phase 5 完成后 git mv 至 `archive/`

## 验收标准

- [x] `assembleRelease` BUILD SUCCESSFUL（7 Active 模块）
- [x] Active 模块 namespace 与主类包根无已知不一致
- [x] `check-docs-health.py` 无 error

## 相关文档

- [ADR-004](../../../docs/decisions/004-loadingview-package-alignment.md)
- [recyclerview-viewbinding.md](../../../docs/guides/recyclerview-viewbinding.md)

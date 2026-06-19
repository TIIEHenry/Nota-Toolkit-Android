---
title: "Phase 2: Namespace 与包名对齐"
type: roadmap
status: archived
phase: 2
updated: 2026-06-19
summary: "Active I/O 模块包迁移、Legacy namespace 修正、ADR-002/003"
---

# Phase 2: Namespace 与包名对齐

## 目标

- [x] ADR-002 namespace 与 Java 包对齐策略
- [x] ADR-003 重复 API 整合策略
- [x] `docs/architecture/concepts/namespace-alignment.md`
- [x] `io-filej`：`tiiehenry.io` → `nota.io.filej`，version 1.5
- [x] `io-encodehelper`：`tiiehenry.io` → `nota.io.encodehelper`，version 1.2
- [x] `android-fragment` namespace → `nota.android.fragment`
- [x] `android-ktx` namespace → `nota.android.ktx`
- [x] Legacy `SimpleTextWatcher` / `CharsetDetector` 添加 `@Deprecated`
- [x] 更新 module-governance、io 模块文档、charset 指南
- [x] `check-docs-health.py` 无 error
- [x] Gradle 构建验证（Phase 5 完成全量 assembleRelease）
- [x] Phase 2 归档

## 相关文档

- [ADR-002](../../../docs/decisions/002-namespace-java-package-alignment.md)
- [namespace-alignment.md](../../../docs/architecture/concepts/namespace-alignment.md)

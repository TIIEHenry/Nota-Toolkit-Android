---
title: "ADR-003: 重复 API 整合策略"
type: decision
status: accepted
phase: 3
updated: 2026-06-19
summary: "SimpleTextWatcher 与 CharsetDetector 重复实现的 canonical 位置与 Legacy 处理"
---

# ADR-003: 重复 API 整合策略

## 背景

项目中存在功能重复的公开类：

| API | Canonical（推荐） | Legacy 副本 |
|-----|-------------------|-------------|
| `SimpleTextWatcher` | `nota.android.text`（android-common） | `tiiehenry.android.text`（android-base） |
| `CharsetDetector` | `nota.text.detector`（android-common） | `tiiehenry.text.detector`（io-encodedetector） |

## 决策

1. **Canonical SSOT** 均在 `android-common` / `nota.*` 包
2. **Legacy 副本**保留源码，添加 `@Deprecated` + `@link` 指向 canonical 类
3. **`io-encodedetector` 模块**维持 Legacy 冻结，不纳入 `settings.gradle`；不在新项目中引用
4. **不删除** Legacy 类（避免破坏已有 Maven 消费方）
5. **文档**：所有指南与 module-governance 决策树指向 canonical API

## 后果

- 新代码统一 import `nota.*`
- Legacy 依赖方有编译期 deprecation 警告，无运行时行为变化
- 长期可在 major version 移除 Legacy 副本（需新 ADR）

## 相关

- [ADR-001](001-nota-package-consolidation.md)
- [charset-detection.md](../guides/charset-detection.md)
- [adapter-framework.md](../architecture/concepts/adapter-framework.md)

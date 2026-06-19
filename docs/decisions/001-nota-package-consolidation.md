---
title: "ADR-001: nota 包名整合"
type: decision
status: accepted
phase: N/A
updated: 2026-06-19
summary: "新代码统一 nota.* 包名，Legacy tiiehenry.* 仅维护不扩展"
---

# ADR-001: nota 包名整合

## 背景

项目经历多次迭代，形成 `tiiehenry.*`（Legacy）与 `nota.*`（新整合）双包名体系。部分 API 重复（如 `SimpleTextWatcher`、CharsetDetector），增加消费方选型成本。

## 决策

1. **新代码**一律使用 `nota.*` 包名
2. **Legacy 模块**（`android-base`, `android-fragment`, `android-ktx`, `io-encodedetector`）不纳入 `settings.gradle`，仅 bugfix
3. **新功能**写入 `android-common` 或新建 Active 模块
4. **Maven groupId** 保持 `tiiehenry.nota.toolkit` 不变（避免破坏已有消费方）
5. Legacy 公开 API 标记 `@Deprecated` 时须指向 `nota.*` 替代

### 关键要点

1. Active 模块清单以 `settings.gradle` 为准
2. 重复 API 文档必须说明选型（见 guides/）
3. namespace 与 Java/Kotlin 包逐步对齐

## 后果

- **正面**：消费方有明确的「推荐路径」；新 Agent/开发者不会误引 Legacy 模块
- **负面**：双包名短期共存；部分 namespace 与包名不一致需后续 phase 修复
- **跟进**：roadmap 中安排 namespace 对齐、重复 API 合并

## 备选方案

- **方案 A**：一次性删除 Legacy 模块 → 可能破坏已有消费方，不选
- **方案 B**：保留双轨 indefinitely → 文档与维护成本持续上升，不选
- **方案 C（选用）**：Legacy 冻结 + nota 扩展 → 渐进迁移，风险可控

## 相关

- [package-naming.md](../architecture/concepts/package-naming.md)
- [module-governance.md](../architecture/module-governance.md)

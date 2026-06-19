---
title: "ADR-002: namespace 与 Java 包对齐"
type: decision
status: accepted
phase: 2
updated: 2026-06-19
summary: "Active 模块 namespace 必须与 Java/Kotlin 包根一致；迁移时 bump minor version"
---

# ADR-002: namespace 与 Java 包对齐

## 背景

AGP 要求每个 library 模块声明 `namespace`，用于生成 `R` 类与 Manifest 合并。部分 Active 模块 namespace 已改为 `nota.*`，但 Java 源码仍留在 `tiiehenry.io`，导致：

- IDE 导航与模块文档不一致
- 消费方 import 路径与 namespace 无对应关系
- Agent / 文档 SSOT 失效

Legacy 模块 `android-fragment` namespace 误设为 `nota.android.ktx`，与 `android-ktx` 冲突。

## 决策

1. **Active 模块**：`namespace` == Java/Kotlin **主包根**（子包允许更深）
2. **包迁移**：`tiiehenry.io` → `nota.io.filej` / `nota.io.encodehelper`（Active I/O 模块）
3. **版本 bump**：破坏性 import 变更时 bump **minor** version（`io-filej` 1.4→1.5，`io-encodehelper` 1.1→1.2）
4. **Legacy 模块**：仅修正 namespace，不强制迁移 Java 包（除非 re-include）
   - `android-fragment`：`nota.android.ktx` → `nota.android.fragment`
   - `android-ktx`：`nota.ktx` → `nota.android.ktx`
5. **Maven groupId** 保持 `tiiehenry.nota.toolkit` 不变（ADR-001）

### 对齐表（Phase 2 目标）

| 模块 | namespace | Java/Kotlin 包根 |
|------|-----------|------------------|
| `io-filej` | `nota.io.filej` | `nota.io.filej` |
| `io-encodehelper` | `nota.io.encodehelper` | `nota.io.encodehelper` |
| `android-fragment` | `nota.android.fragment` | `tiiehenry.android.fragment.*`（Legacy，暂不迁包） |
| `android-ktx` | `nota.android.ktx` | `nota.android.ktx.*` |

## 后果

- **正面**：文档、namespace、import 三者一致；减少 Agent 与消费者困惑
- **负面**：`io-filej` / `io-encodehelper` 消费方须改 import（minor bump  signaling）
- **跟进**：Legacy `tiiehenry.android.fragment` 包迁移列入后续 Phase

## 备选方案

- **仅改文档不改包** → 技术债持续，不选
- **保留 tiiehenry.io + 新增 nota.io 桥接类** → 重复 API，违背 ADR-001，不选

## 相关

- [ADR-001](001-nota-package-consolidation.md)
- [namespace-alignment.md](../architecture/concepts/namespace-alignment.md)

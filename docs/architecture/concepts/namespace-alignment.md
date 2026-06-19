---
title: "Namespace 对齐约定"
type: concept
status: accepted
phase: 2
updated: 2026-06-19
summary: "Gradle namespace 与 Java/Kotlin 包根的对应规则与例外"
---

# Namespace 对齐约定

## 规则

```
android { namespace = "<主包根>" }
```

Java/Kotlin 公开 API 的主包根 **必须** 与 `namespace` 一致或为其子包。

| 项 | 说明 |
|----|------|
| `R` 类 | 生成于 `namespace` 包下 |
| Kotlin/Java 源码 | 放在 `src/main/java/<namespace/path>/` |
| 子包 | 允许，如 `nota.android.widget.recyclerview` |

## Phase 2 已对齐（Active）

| 模块 | namespace | 包根 |
|------|-----------|------|
| `io-filej` | `nota.io.filej` | `nota.io.filej` |
| `io-encodehelper` | `nota.io.encodehelper` | `nota.io.encodehelper` |
| `android-common` | `nota.android` | `nota.android.*` / `nota.*` |
| Widget 模块 | `nota.android.widget.<name>` | 与 namespace 一致 |
| `LoadingView` | `nota.android.widget.loadingview` | Phase 5 已对齐（ADR-004，v1.1） |

## Legacy 已知例外（仅 namespace 修正，Phase 6 已完成 base/fragment 包迁移）

| 模块 | 状态 |
|------|------|
| `android-ktx` | 未纳入 settings.gradle，包 `nota.android.ktx.*` |
| `io-encodedetector` | 冻结，包仍为 `tiiehenry.text.detector.*` |

## 新增模块检查清单

- [ ] `build.gradle` 中 `namespace` 已设
- [ ] 源码目录与 namespace 路径一致
- [ ] 文档 `docs/architecture/modules/` 已更新
- [ ] 若改公开 import → bump version + 更新 guides

## 相关文档

- [ADR-002](../../decisions/002-namespace-java-package-alignment.md)
- [package-naming.md](package-naming.md)
- [module-governance.md](../module-governance.md)

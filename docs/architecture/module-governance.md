---
title: "模块治理"
type: architecture
status: accepted
phase: N/A
updated: 2026-06-19
summary: "Active/Legacy 模块分类、Maven 坐标、依赖选型决策树"
---

# 模块治理

> Nota-Toolkit-Android 的模块边界、发布策略与消费方选型指南。

## 模块全景

```
Nota-Toolkit-Android/
├── android-common              ← 核心整合库（优先依赖）
├── android-widget-loadingview  ← 独立 Widget
├── android-widget-searchview
├── android-widget-treeview
├── android-widget-flowtag
├── io-filej                    ← I/O
├── io-encodehelper
├── android-base                ← Legacy Adapter（构建验证，v2.0）
├── android-fragment            ← Legacy Fragment Pager（v2.0）
│
├── [Legacy — 未纳入 settings.gradle]
├── android-ktx                 ← 纯 Kotlin 扩展
└── io-encodedetector           ← ICU CharsetDetector（重复）
```

## Active 模块清单

| Module | Version | Namespace | 包根 | 发布 |
|--------|---------|-----------|------|------|
| `android-common` | 1.0 | `nota.android` | `nota.*` | ✅ |
| `android-widget-loadingview` | 1.1 | `nota.android.widget.loadingview` | `nota.android.widget.loadingview` | ✅ |
| `android-widget-searchview` | 1.1 | `nota.android.widget.searchview` | `nota.android.widget.searchview` | ✅ |
| `android-widget-treeview` | 1.1 | `nota.android.widget.treeview` | `nota.android.widget.treeview` | ✅ |
| `android-widget-flowtag` | 1.0 | `nota.android.widget.flowtag` | `nota.android.widget.flowtag` | ✅ |
| `io-filej` | 1.5 | `nota.io.filej` | `nota.io.filej` | ✅ |
| `io-encodehelper` | 1.2 | `nota.io.encodehelper` | `nota.io.encodehelper` | ✅ |

## Legacy 模块清单

| Module | Version | 构建 | 包根 | 说明 |
|--------|---------|------|------|------|
| `android-base` | 2.0 | ✅ settings.gradle | `nota.android.base.*` | Legacy Adapter，新项用 android-common |
| `android-fragment` | 2.0 | ✅ settings.gradle | `nota.android.fragment.*` | 依赖 base 2.0 |
| `android-ktx` | 1.0 | ❌ | `nota.android.ktx.*` | 部分扩展已迁入 common |
| `io-encodedetector` | 67.1 | ❌ | `tiiehenry.text.detector.*` | 冻结，用 common ICU |

## 消费方选型决策树

```
需要 RecyclerView 列表？
├── 新项目 + ViewBinding → android-common (VBRecyclerAdapter)
└── Legacy 项目 → android-base:2.0 + android-fragment:2.0（见 legacy-package-migration 指南）

需要自定义 Widget？
└── 按需引入对应 android-widget-* 模块（互不依赖）

需要文件操作？
└── io-filej (Filej)

需要编码检测？
├── 轻量 / 已有 cpdetector → io-encodehelper
└── ICU / 已在 common 依赖 → android-common (nota.text.detector)
```

## 发布约定

- **groupId**：`tiiehenry.nota.toolkit`（所有模块统一）
- **artifactId**：Gradle 模块名（如 `android-common`）
- **发布命令**：`./gradlew :<module>:publishModulePublicationToMavenLocal`
- **配置**：[`gradle/maven-publish.gradle`](../../gradle/maven-publish.gradle)

## 已知不一致（待后续 Phase）

1. `android-ktx` / `io-encodedetector` 未纳入 `settings.gradle`
2. Charset Legacy 模块 `io-encodedetector` 仍冻结（canonical 在 android-common）

## 相关文档

- [modules/README.md](modules/README.md) — 各模块详情索引
- [guides/legacy-package-migration.md](../guides/legacy-package-migration.md)
- [concepts/package-naming.md](concepts/package-naming.md) — 包名迁移约定
- [ADR-005](../decisions/005-legacy-adapter-package-migration.md)
- [ADR-001](../decisions/001-nota-package-consolidation.md) — 包名整合决策
- [guides/getting-started.md](../guides/getting-started.md) — 接入指南

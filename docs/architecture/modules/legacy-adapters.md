---
title: "Legacy Adapter 模块"
type: architecture
status: accepted
phase: 6
updated: 2026-06-19
summary: "android-base 2.0 与 android-fragment 2.0，Legacy 维护模式"
---

# Legacy Adapter 模块

> **状态**：Legacy — 已纳入 `settings.gradle` 构建验证；新功能写入 `android-common`，此处仅维护。

## android-base (v2.0)

| 项 | 值 |
|----|-----|
| namespace | `nota.android.base` |
| 包根 | `nota.android.base.view.*` / `nota.android.base.text.*` |
| 核心 | `IViewHolder`, `AbstractListAdapter`, `AbstractRecyclerAdapter` |

### 包结构

```
nota.android.base.view.base       IViewHolder, IAdapter, INotifier
nota.android.base.view.listview   ListView 适配器
nota.android.base.view.recyclerview  RecyclerView 适配器
nota.android.base.view.spinner    Spinner 适配器
nota.android.base.text            SimpleTextWatcher (@Deprecated)
```

## android-fragment (v2.0)

| 项 | 值 |
|----|-----|
| namespace | `nota.android.fragment` |
| 包根 | `nota.android.fragment.*` |
| 依赖 | `:android-base` |
| 核心 | `DynamicFragmentStateAdapter`, `FragmentNoStatePagerAdapter`, `StateFragment` |

## 迁移对照

| 旧 import (v1.x) | 新 import (v2.0) |
|------------------|------------------|
| `tiiehenry.android.view.recyclerview.adapter.*` | `nota.android.base.view.recyclerview.adapter.*` |
| `tiiehenry.android.fragment.adapter.*` | `nota.android.fragment.adapter.*` |

完整对照见 [legacy-package-migration.md](../../guides/legacy-package-migration.md)。

## 迁移路径

| Legacy | 替代 |
|--------|------|
| `AbstractRecyclerAdapter` | `VBRecyclerAdapter`（android-common） |
| `SimpleTextWatcher` | `nota.android.text.SimpleTextWatcher`（android-common） |
| `FragmentNoStatePagerAdapter` | 按需保留或迁移 AndroidX Fragment API |

## 相关文档

- [ADR-005](../../decisions/005-legacy-adapter-package-migration.md)
- [concepts/adapter-framework.md](../concepts/adapter-framework.md)
- [android-common.md](android-common.md)

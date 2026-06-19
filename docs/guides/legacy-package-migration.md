---
title: "Legacy 包迁移指南"
type: guide
status: accepted
phase: 6
updated: 2026-06-19
summary: "android-base 2.0 / android-fragment 2.0 import 变更对照"
---

# Legacy 包迁移指南

> ADR-005：`tiiehenry.android.*` → `nota.android.base.*` / `nota.android.fragment.*`

## 版本

| 模块 | 旧版本 | 新版本 |
|------|--------|--------|
| `android-base` | 1.2 | **2.0** |
| `android-fragment` | 1.1 | **2.0** |

## Gradle 依赖

```groovy
implementation 'tiiehenry.nota.toolkit:android-base:2.0'
implementation 'tiiehenry.nota.toolkit:android-fragment:2.0'  // 依赖 base 2.0
```

源码依赖：

```groovy
include ':android-base', ':android-fragment'
implementation project(':android-fragment')  // 传递依赖 base
```

## Import 对照

| 旧 import | 新 import |
|-----------|-----------|
| `tiiehenry.android.view.recyclerview.adapter.AbstractRecyclerAdapter` | `nota.android.base.view.recyclerview.adapter.AbstractRecyclerAdapter` |
| `tiiehenry.android.view.base.holder.IViewHolder` | `nota.android.base.view.base.holder.IViewHolder` |
| `tiiehenry.android.view.listview.adapter.AbstractListAdapter` | `nota.android.base.view.listview.adapter.AbstractListAdapter` |
| `tiiehenry.android.text.SimpleTextWatcher` | `nota.android.base.text.SimpleTextWatcher`（**已弃用**，用 `nota.android.text.SimpleTextWatcher`） |
| `tiiehenry.android.fragment.adapter.DynamicFragmentStateAdapter` | `nota.android.fragment.adapter.DynamicFragmentStateAdapter` |
| `tiiehenry.android.fragment.adapter.FragmentNoStatePagerAdapter` | `nota.android.fragment.adapter.FragmentNoStatePagerAdapter` |

## 推荐路径

**新项目不要引入 android-base / android-fragment**。改用：

```groovy
implementation 'tiiehenry.nota.toolkit:android-common:1.0'
```

见 [recyclerview-viewbinding.md](recyclerview-viewbinding.md)。

## 相关文档

- [ADR-005](../decisions/005-legacy-adapter-package-migration.md)
- [legacy-adapters.md](../architecture/modules/legacy-adapters.md)

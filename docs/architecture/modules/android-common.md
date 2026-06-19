---
title: "android-common 模块"
type: architecture
status: accepted
phase: N/A
updated: 2026-06-19
summary: "核心整合库：ViewBinding Adapter、LiveData、Crypto、Reflection、Charset、View 扩展"
---

# android-common 模块

> artifactId: `android-common` · version: `1.0` · namespace: `nota.android`

## 概述

项目最大的 Active 模块，整合 Adapter 框架（ViewBinding 版）、Android 生命周期工具、自定义 View、加密/反射/编码检测等通用能力。新功能优先写入本模块。

## 包结构

| 包 | 职责 | 关键类 |
|----|------|--------|
| `nota.android.widget.recyclerview` | ViewBinding RecyclerView | `VBRecyclerAdapter`, `VBListRecyclerAdapter` |
| `nota.android.app.lifecycle` | LiveData 扩展 | `OnceLiveData`, `PersistLiveData`, `SharedPreferenceLiveData` |
| `nota.android.app.fragment.page` | Fragment 分页 | `IFragmentPage`, `FragmentPageAnimator` |
| `nota.android.view` | 自定义 View / 扩展 | `SwipeLayout`, `SwipeRecyclerView`, `ViewExt.kt` |
| `nota.crypto` | 加密工具 | `EncryptUtils`, `Md5Utils`, `ShaUtils` |
| `nota.lang.reflect` | 反射封装 | `ReflectBuilder`, `ReflectHelper` |
| `nota.text.detector` | ICU 编码检测 | `CharsetDetector` |
| `nota.io` | 流复制 | `StreamParallelCopier` |

## 核心设计

### ViewBinding Adapter

`VBRecyclerAdapter` 通过反射解析泛型参数获取 ViewBinding 类，自动 inflate 并绑定。适用于新项目，替代 Legacy `AbstractRecyclerAdapter`。

```kotlin
class MyAdapter : VBRecyclerAdapter<ItemBinding, MyItem>() {
    override fun onBind(binding: ItemBinding, item: MyItem, position: Int) {
        binding.title.text = item.title
    }
}
```

### LiveData 语义

| 类 | 语义 |
|----|------|
| `OnceLiveData` | 一次性事件，消费后不再触发 |
| `PersistLiveData` | 持久化到 SharedPreferences |
| `SharedPreferenceLiveData` | SP 键值双向绑定 |

## 依赖

- `compileOnly` AndroidX（appcompat, fragment-ktx, recyclerview）
- 消费方须自行提供 AndroidX 运行时依赖

## 相关文档

- [concepts/adapter-framework.md](../concepts/adapter-framework.md)
- [guides/recyclerview-viewbinding.md](../../guides/recyclerview-viewbinding.md)
- [legacy-adapters.md](legacy-adapters.md)
- [guides/charset-detection.md](../../guides/charset-detection.md)

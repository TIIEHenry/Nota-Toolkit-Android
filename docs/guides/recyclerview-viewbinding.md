---
title: "RecyclerView ViewBinding Adapter 指南"
type: guide
status: accepted
phase: 5
updated: 2026-06-19
summary: "android-common 中 VBRecyclerAdapter / VBListRecyclerAdapter 用法"
---

# RecyclerView ViewBinding Adapter 指南

> 模块：`android-common` · 推荐替代 Legacy `AbstractRecyclerAdapter`

## 依赖

```groovy
implementation 'tiiehenry.nota.toolkit:android-common:1.0'
// 运行时须自行提供 AndroidX
implementation 'androidx.recyclerview:recyclerview:1.4.0'
```

## VBRecyclerAdapter — 多类型列表

适用于多种 item 布局（多 ViewBinding 类型）：

```kotlin
class UserAdapter : VBRecyclerAdapter<ItemUserBinding, User>() {
    override fun onBind(binding: ItemUserBinding, item: User, position: Int) {
        binding.name.text = item.name
    }
}
```

泛型 `VB : ViewBinding` 通过反射从子类泛型参数解析，自动 inflate 对应 binding。

## VBListRecyclerAdapter — 单一布局

列表项共用同一 layout 时使用，API 更简洁：

```kotlin
class SimpleAdapter : VBListRecyclerAdapter<ItemRowBinding, String>() {
    override fun onBind(binding: ItemRowBinding, item: String, position: Int) {
        binding.text.text = item
    }
}
```

## 与 Legacy Adapter 对比

| 项 | VB Adapter | Legacy AbstractRecyclerAdapter |
|----|------------|-------------------------------|
| 模块 | android-common | android-base (Legacy) |
| View 绑定 | ViewBinding | findViewById / 手动 |
| 语言 | Kotlin 优先 | Java |
| 推荐 | ✅ 新项目 | 仅维护旧项目 |

详见 [adapter-framework.md](../architecture/concepts/adapter-framework.md)。

## 拖拽与触摸

模块还提供 `ItemTouchHelper` 相关 callback（`recyclerview` 包下），可与 Adapter 组合实现拖拽排序。

## 相关文档

- [android-common.md](../architecture/modules/android-common.md)
- [legacy-adapters.md](../architecture/modules/legacy-adapters.md)

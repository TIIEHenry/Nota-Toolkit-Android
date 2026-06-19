---
title: "Adapter 框架约定"
type: concept
status: accepted
phase: N/A
updated: 2026-06-19
summary: "Legacy Adapter 与 ViewBinding Adapter 并存时的选型与 API 约定"
---

# Adapter 框架约定

## 两套 Adapter 体系

| 体系 | 模块 | 基类 | 适用场景 |
|------|------|------|----------|
| Legacy | `android-base` 2.0 | `AbstractRecyclerAdapter` | 已有 Legacy 依赖的项目 |
| ViewBinding | `android-common` | `VBRecyclerAdapter` | 新项目、ViewBinding 项目 |

## 选型原则

- **默认推荐** `VBRecyclerAdapter`：类型安全、无 findViewById
- **Legacy 项目**继续用 `AbstractRecyclerAdapter`，不强制迁移
- **禁止**在新模块中引入 `android-base` 依赖

## ViewBinding Adapter 约定

1. 子类声明两个泛型：`VB : ViewBinding`, `T : 数据类型`
2. `onBind(binding, item, position)` 中完成 UI 绑定
3. 可选 override `onCreateBinding(parent)` 自定义 inflate

## Legacy Adapter 约定

1. ViewHolder 实现 `IViewHolder<T, VH>`
2. 泛型自引用：`class MyAdapter : AbstractRecyclerAdapter<MyAdapter, Item, MyVH>()`
3. `onCreateViewHolder` / `onBindViewHolder` 标准 RecyclerView 模式

## 相关文档

- [modules/android-common.md](../modules/android-common.md)
- [modules/legacy-adapters.md](../modules/legacy-adapters.md)

---
title: "Widget 模块"
type: architecture
status: accepted
phase: N/A
updated: 2026-06-19
summary: "android-widget-* 四个独立自定义 View 模块总览"
---

# Widget 模块

四个 Active Widget 模块互不依赖，可单独引入。

## 模块一览

| Module | Version | 核心类 | 说明 |
|--------|---------|--------|------|
| `android-widget-loadingview` | 1.1 | `LoadingView` | 圆形加载动画（Kotlin） |
| `android-widget-searchview` | 1.1 | `SearchLayout` | 自定义搜索栏 |
| `android-widget-treeview` | 1.1 | `TreeViewAdapter`, `TreeNode`, `TreeBinder` | RecyclerView 树形列表 |
| `android-widget-flowtag` | 1.0 | `FlowTagLayout`, `TagAdapter` | 流式标签，支持单选/多选 |

## 设计原则

1. **独立模块**：每个 Widget 自带 `res/layout`、`res/drawable`，不依赖 `android-common`
2. **XML 配置**：通过自定义 attrs 暴露配置项（如 FlowTag 选择模式）
3. **Java/Kotlin 混用**：Legacy Widget 为 Java；`LoadingView` 为 Kotlin

## FlowTagLayout 选择模式

| 模式 | 行为 |
|------|------|
| 单选 | 选中一项，取消其他 |
| 多选 | 独立 toggle |
| 无选 | 仅展示，不可交互 |

## TreeView 绑定

`TreeBinder` 定义节点渲染逻辑；`TreeViewAdapter` 管理展开/折叠状态。详见 `TreeNode` 父子关系 API。

## 使用指南

| Widget | 指南 |
|--------|------|
| LoadingView | [widget-loadingview.md](../../guides/widget-loadingview.md) |
| FlowTagLayout | [widget-flowtag.md](../../guides/widget-flowtag.md) |
| TreeView | [widget-treeview.md](../../guides/widget-treeview.md) |
| SearchLayout | [widget-searchview.md](../../guides/widget-searchview.md) |

## 相关文档

- [module-governance.md](../module-governance.md)
- [guides/getting-started.md](../../guides/getting-started.md)

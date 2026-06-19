---
title: "TreeView 使用指南"
type: guide
status: accepted
phase: 4
updated: 2026-06-19
summary: "树形 RecyclerView：TreeNode、TreeBinder、TreeViewAdapter 用法"
---

# TreeView 使用指南

> 模块：`android-widget-treeview`

## 依赖

```groovy
implementation 'tiiehenry.nota.toolkit:android-widget-treeview:1.1'
```

## 核心类

| 类 | 职责 |
|----|------|
| `TreeNode<T>` | 树节点，含子节点列表与展开状态 |
| `TreeBinder<T, VH>` | 节点类型 → ViewHolder 绑定 |
| `TreeViewAdapter<T, VH>` | RecyclerView 适配器，管理展平显示列表 |
| `TreeViewViewHolder` | ViewHolder 基类 |

## 基本流程

```java
// 1. 定义数据项（实现 LayoutItemType 提供 layoutId）
public class FileItem implements LayoutItemType {
    public String name;
    @Override public int getLayoutId() { return R.layout.item_file; }
}

// 2. 实现 TreeBinder
TreeBinder<FileItem, FileViewHolder> binder = new TreeBinder<FileItem, FileViewHolder>() {
    @Override
    public FileViewHolder provideViewHolder(View itemView) {
        return new FileViewHolder(itemView);
    }
    @Override
    public void bindView(FileViewHolder holder, int position, FileItem item) {
        holder.title.setText(item.name);
    }
};

// 3. 构建树
TreeNode<FileItem> root = new TreeNode<>(new FileItem("root"));
root.addChild(new TreeNode<>(new FileItem("child")));

// 4. 设置 Adapter
List<TreeBinder<FileItem, FileViewHolder>> binders = Collections.singletonList(binder);
TreeViewAdapter<FileItem, FileViewHolder> adapter =
    new TreeViewAdapter<>(Collections.singletonList(root), binders);
recyclerView.setAdapter(adapter);
```

## 展开 / 折叠

- 点击带 `isBranch()` 的节点触发 `TreeViewAdapter` 内部展开逻辑
- `setPadding(int)` 控制层级缩进（默认 30px）
- `setToCollapseChild(boolean)` 折叠父节点时是否同时折叠子树

## Diff 更新

Adapter 提供 `updateNodes(List<TreeNode<T>>)` 配合 `DiffUtil` 刷新，保持展开状态（通过 `KEY_IS_EXPAND` Bundle）。

## 相关文档

- [widgets.md](../architecture/modules/widgets.md)

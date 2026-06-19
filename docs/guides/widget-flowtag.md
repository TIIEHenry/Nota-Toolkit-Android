---
title: "FlowTagLayout 使用指南"
type: guide
status: accepted
phase: 4
updated: 2026-06-19
summary: "流式标签布局：选择模式、XML 配置与 Adapter 用法"
---

# FlowTagLayout 使用指南

> 模块：`android-widget-flowtag` · 类：`nota.android.widget.flowtag.FlowTagLayout`

## 依赖

```groovy
implementation 'tiiehenry.nota.toolkit:android-widget-flowtag:1.0'
```

## 选择模式

| 模式 | `ftl_check_mode` | 行为 |
|------|------------------|------|
| 不可选 | `none` (0) | 默认，无交互 |
| 单选 | `single` (1) | 选中一项；`ftl_single_cancelable=true` 可取消 |
| 多选 | `multi` (2) | 独立 toggle |
| 仅展示 | `display` (3) | 无选中态 |

## XML 示例

```xml
<nota.android.widget.flowtag.FlowTagLayout
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:ftl_check_mode="multi"
    app:ftl_entries="@array/tags"
    app:ftl_selecteds="@array/default_selected"
    app:ftl_single_cancelable="false" />
```

## 自定义属性

| 属性 | 格式 | 说明 |
|------|------|------|
| `ftl_check_mode` | enum | `none` / `single` / `multi` / `display` |
| `ftl_entries` | reference | 标签字符串数组资源 |
| `ftl_selecteds` | reference | 默认选中索引数组 |
| `ftl_single_cancelable` | boolean | 单选模式下是否可取消 |

## 代码：Adapter 模式

```java
FlowTagLayout tagLayout = findViewById(R.id.tags);
TagAdapter<String> adapter = new TagAdapter<String>(tagList) {
    @Override
    public View getView(FlowTagLayout parent, int position, String item) {
        TextView tv = (TextView) LayoutInflater.from(parent.getContext())
            .inflate(R.layout.tag_item, parent, false);
        tv.setText(item);
        return tv;
    }
};
tagLayout.setAdapter(adapter);
tagLayout.setOnTagClickListener((parent, view, position) -> { /* ... */ });
```

## 相关文档

- [widgets.md](../architecture/modules/widgets.md)

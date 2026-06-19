---
title: "SearchLayout 使用指南"
type: guide
status: accepted
phase: 4
updated: 2026-06-19
summary: "android-widget-searchview 搜索栏布局集成"
---

# SearchLayout 使用指南

> 模块：`android-widget-searchview` · 类：`nota.android.widget.searchview.SearchLayout`

## 依赖

```groovy
implementation 'tiiehenry.nota.toolkit:android-widget-searchview:1.1'
```

## XML 布局

模块自带布局资源，可直接 inflate 或嵌入：

```xml
<nota.android.widget.searchview.SearchLayout
    android:id="@+id/search_layout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

`SearchLayout` 为 `FrameLayout` 子类，内部组合搜索输入框与清除按钮（见模块 `src/main/res/layout/`）。

## 代码监听

```java
SearchLayout searchLayout = findViewById(R.id.search_layout);
searchLayout.inflate(LayoutInflater.from(this));

searchLayout.setOnQueryTextListener(new OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
    @Override
    public boolean onQueryTextChange(String newText) {
        // 实时搜索
        return true;
    }
});

EditText input = searchLayout.getEditText();
String keyword = searchLayout.getSearchText();
searchLayout.clearSearchText();
```

> **提示**：新代码请使用 `nota.android.text.SimpleTextWatcher`（android-common），勿引 Legacy `tiiehenry.android.text`。

## 样式定制

通过模块 `res/drawable/` 与 `res/layout/` 覆盖资源，或在宿主 App 主题中定义同名资源实现替换。

## 相关文档

- [widgets.md](../architecture/modules/widgets.md)

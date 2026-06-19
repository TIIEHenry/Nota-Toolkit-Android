---
title: "LoadingView 使用指南"
type: guide
status: accepted
phase: 4
updated: 2026-06-19
summary: "android-widget-loadingview 依赖引入、XML 属性与代码配置"
---

# LoadingView 使用指南

> 模块：`android-widget-loadingview` · 类：`nota.android.widget.loadingview.LoadingView`

## 依赖

```groovy
implementation 'tiiehenry.nota.toolkit:android-widget-loadingview:1.1'
```

## XML 布局

```xml
<nota.android.widget.loadingview.LoadingView
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="48dp"
    android:layout_height="48dp"
    app:lvColor="@color/primary"
    app:lvStrokeWidth="3dp"
    app:lvSweepAngle="180"
    app:lvMaxSpeed="8"
    app:lvMinSpeed="2" />
```

## 自定义属性

| 属性 | 格式 | 说明 |
|------|------|------|
| `lvCount` | integer | 圆弧段数 |
| `lvStartAngle` | integer | 起始角度（度） |
| `lvSweepAngle` | integer | 扫描角度（度） |
| `lvStrokeWidth` | dimension | 线宽 |
| `lvMaxSpeed` | integer | 最大转速 |
| `lvMinSpeed` | integer | 最小转速 |
| `lvCirclePadding` | dimension | 内边距 |
| `lvRefreshInterval` | integer | 刷新间隔（ms） |
| `lvColor` | color | 圆弧颜色 |
| `lvCounterclockwise` | boolean | 逆时针 |
| `lvAlternatingRotation` | boolean | 交替旋转 |

## 代码控制

```kotlin
val loading = findViewById<LoadingView>(R.id.loading)
loading.visibility = View.VISIBLE  // 显示动画
loading.visibility = View.GONE     // 停止绘制
```

## 相关文档

- [widgets.md](../architecture/modules/widgets.md)

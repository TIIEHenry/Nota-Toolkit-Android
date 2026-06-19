---
title: "ADR-004: LoadingView 包名对齐"
type: decision
status: accepted
phase: 5
updated: 2026-06-19
summary: "LoadingView 迁至 nota.android.widget.loadingview，与 namespace 一致"
---

# ADR-004: LoadingView 包名对齐

## 背景

`android-widget-loadingview` 的 `namespace` 为 `nota.android.widget.loadingview`，但 `LoadingView` 类位于 `nota.android.widget`，导致 XML 全限定类名与 namespace 不一致（ADR-002 违规）。

## 决策

1. 将 `LoadingView` 迁至 `nota.android.widget.loadingview` 包
2. 模块 version bump：**1.0 → 1.1**
3. XML 使用 `<nota.android.widget.loadingview.LoadingView ... />`
4. Kotlin import：`import nota.android.widget.loadingview.LoadingView`

## 后果

- 消费方若 XML 写旧类名须更新（minor bump）
- 与 namespace、R 类包路径完全一致

## 相关

- [ADR-002](002-namespace-java-package-alignment.md)
- [widget-loadingview.md](../guides/widget-loadingview.md)

---
title: "术语表"
type: concept
status: accepted
phase: N/A
updated: 2026-06-19
summary: "Nota-Toolkit 术语单一事实源，供各文档引用"
---

# 术语表

> **单一事实源**：其他文档引用本表中的术语定义，不重复定义。

---

## 项目与模块

| 术语 | 说明 |
|------|------|
| **Nota-Toolkit** | 本仓库，多模块 Android Library 工具包 |
| **Active Module** | 在 `settings.gradle` 中 `include` 的模块，可正常构建与发布 |
| **Legacy Module** | 维护模式模块；`android-base`/`android-fragment` 已纳入构建（v2.0），`android-ktx`/`io-encodedetector` 未纳入 |
| **Maven 坐标** | `tiiehenry.nota.toolkit:<artifactId>:<version>` |

## 包名

| 术语 | 说明 |
|------|------|
| **`nota.*`** | 新整合包名空间，新代码默认使用 |
| **`tiiehenry.*`** | Legacy 包名空间，仅维护不扩展 |

## Adapter 框架

| 术语 | 说明 |
|------|------|
| **Legacy Adapter** | `nota.android.base.view.*` 下 Adapter 框架（android-base 2.0） |
| **VB Adapter** | `android-common` 中基于 ViewBinding 的 `VBRecyclerAdapter` / `VBListRecyclerAdapter` |
| **IViewHolder** | Legacy ViewHolder 接口，泛型自引用模式 `IADAPTER extends ...` |

## Widget

| 术语 | 说明 |
|------|------|
| **Widget Module** | `android-widget-*` 独立自定义 View 模块 |
| **FlowTag** | 流式标签布局，支持单选/多选模式 |
| **TreeView** | 基于 RecyclerView 的树形列表 |

## I/O 与编码

| 术语 | 说明 |
|------|------|
| **Filej** | `io-filej` 模块中 `File` 的子类，增强 copy/read/write/zip |
| **EncodeHelper** | `io-encodehelper` 模块，基于 cpdetector JAR 的编码检测 |
| **CharsetDetector (ICU)** | `android-common` 与 `io-encodedetector` 中的 ICU 编码检测实现 |

## 文档系统

| 术语 | 说明 |
|------|------|
| **Phase** | 开发阶段，记录在 `dev/roadmap/phaseN_*.md` |
| **ADR** | Architecture Decision Record，存储在 `docs/decisions/` |
| **知识层** | `docs/` — 稳定设计文档 |
| **行动层** | `dev/` — roadmap、进度、验证 |

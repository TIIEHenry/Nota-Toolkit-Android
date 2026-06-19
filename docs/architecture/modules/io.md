---
title: "I/O 模块"
type: architecture
status: accepted
phase: N/A
updated: 2026-06-19
summary: "io-filej 与 io-encodehelper 模块设计"
---

# I/O 模块

## io-filej

| 项 | 值 |
|----|-----|
| Version | 1.5 |
| Namespace | `nota.io.filej` |
| Java 包 | `nota.io.filej` |

### 核心 API

- **`Filej`** — `File` 子类，链式增强 copy / read / write / delete / zip
- **`Zipl`** — ZIP 压缩/解压工具

### 设计要点

- 方法返回 `Filej` 自身或新 `Filej` 实例，支持链式调用
- 读写默认 UTF-8，可通过 overload 指定编码

## io-encodehelper

| 项 | 值 |
|----|-----|
| Version | 1.2 |
| 依赖 | `libs/` 下 cpdetector JAR |
| Java 包 | `nota.io.encodehelper` |

### 核心 API

- **`EncodeHelper`** — 文件/字节流编码检测，基于 cpdetector

### 与 android-common 的关系

`android-common` 内置 ICU `CharsetDetector`（`nota.text.detector`），与本模块功能重叠。选型见 [charset-detection.md](../../guides/charset-detection.md)。

## 相关文档

- [guides/charset-detection.md](../../guides/charset-detection.md)
- [module-governance.md](../module-governance.md)

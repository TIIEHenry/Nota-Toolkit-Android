---
title: "编码检测选型指南"
type: guide
status: accepted
phase: N/A
updated: 2026-06-19
summary: "三套编码检测实现的对比与选型"
---

# 编码检测选型指南

Nota-Toolkit 中存在三套编码检测实现，功能重叠但依赖与包名不同。

## 对比

| 实现 | 模块 | 包 | 底层 | 状态 |
|------|------|-----|------|------|
| ICU CharsetDetector | `android-common` | `nota.text.detector` | ICU4J 移植 | Active，推荐 |
| ICU CharsetDetector | `io-encodedetector` | `tiiehenry.text.detector` | ICU4J 移植 | Legacy，与上重复 |
| EncodeHelper | `io-encodehelper` | `nota.io.encodehelper` | cpdetector JAR | Active，独立模块 |

## 选型建议

| 场景 | 推荐 |
|------|------|
| 已依赖 `android-common` | 直接用 `nota.text.detector.CharsetDetector` |
| 仅需编码检测、不想引 Android 库 | `io-encodehelper`（自带 cpdetector JAR） |
| Legacy 项目已用 cpdetector | 继续 `io-encodehelper` |
| 新项目 | **不要**引入 `io-encodedetector` |

## API 示例

### android-common (ICU)

```java
CharsetDetector detector = new CharsetDetector();
detector.setText(bytes);
CharsetMatch match = detector.detect();
String encoding = match.getName();
```

```java
import nota.io.encodehelper.EncodeHelper;
String encoding = EncodeHelper.detect(file);
```

## 相关文档

- [modules/io.md](../architecture/modules/io.md)
- [modules/android-common.md](../architecture/modules/android-common.md)

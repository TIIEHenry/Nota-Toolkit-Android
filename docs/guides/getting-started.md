---
title: "快速上手"
type: guide
status: accepted
phase: N/A
updated: 2026-06-19
summary: "依赖引入、本地发布、模块选型"
---

# 快速上手

## 方式一：Maven Local 发布

```bash
# 发布单个模块
./gradlew :android-common:publishModulePublicationToMavenLocal

# 发布所有 Active 模块
./gradlew publishToMavenLocal
```

消费方 `build.gradle`：

```groovy
repositories {
    mavenLocal()
}

dependencies {
    implementation 'tiiehenry.nota.toolkit:android-common:1.0'
    implementation 'tiiehenry.nota.toolkit:android-widget-flowtag:1.0'
}
```

## 方式二：源码依赖

在消费方 `settings.gradle` 中 include 本仓库模块，使用 `project(':android-common')`。

## 模块选型

| 需求 | 模块 |
|------|------|
| RecyclerView + ViewBinding | `android-common` |
| 加载动画 | `android-widget-loadingview` |
| 搜索栏 | `android-widget-searchview` |
| 树形列表 | `android-widget-treeview` |
| 流式标签 | `android-widget-flowtag` |
| 文件增强 | `io-filej` |
| 编码检测 | 见 [charset-detection.md](charset-detection.md) |

## 环境要求

- JDK 17
- Android SDK 36
- Gradle 9.1+（wrapper 已配置）

## 相关文档

- [module-governance.md](../architecture/module-governance.md)
- [publish-to-maven-local.md](publish-to-maven-local.md)

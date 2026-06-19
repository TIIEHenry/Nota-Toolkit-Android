---
title: "发布到 Maven Local"
type: guide
status: accepted
phase: N/A
updated: 2026-06-19
summary: "AAR + sources 发布流程与 POM 依赖注入说明"
---

# 发布到 Maven Local

## 发布命令

```bash
./gradlew :<module-name>:publishModulePublicationToMavenLocal
```

示例：

```bash
./gradlew :android-common:publishModulePublicationToMavenLocal
./gradlew :io-filej:publishModulePublicationToMavenLocal
```

## 发布产物

| 产物 | 说明 |
|------|------|
| `<module>-<version>.aar` | Release AAR |
| `<module>-<version>-sources.jar` | 源码 JAR |

配置来源：[`gradle/maven-publish.gradle`](../../gradle/maven-publish.gradle)

## POM 依赖

发布脚本自动将 `implementation` 和 `api` 依赖写入 POM。`compileOnly` 依赖**不会**写入 POM — 消费方须自行添加 AndroidX 等运行时依赖。

## 坐标格式

```
groupId:    tiiehenry.nota.toolkit
artifactId: <gradle-module-name>
version:    <module build.gradle 中的 version>
```

## 相关文档

- [getting-started.md](getting-started.md)
- [module-governance.md](../architecture/module-governance.md)

# Nota-Toolkit-Android

多模块 Android Library 工具包 — Adapter 框架、自定义 Widget、I/O 与编码检测、Kotlin 扩展。

> **文档入口**：[docs/README.md](docs/README.md) · **Agent 入口**：[AGENTS.md](AGENTS.md)

## Active 模块

| Module | 说明 |
|--------|------|
| `android-common` | 核心整合库（ViewBinding Adapter、LiveData、Crypto 等） |
| `android-widget-*` | LoadingView、SearchLayout、TreeView、FlowTagLayout |
| `io-filej` | 增强 File API |
| `io-encodehelper` | cpdetector 编码检测 |

完整模块地图见 [docs/architecture/module-governance.md](docs/architecture/module-governance.md)。

## 快速开始

```bash
./gradlew publishToMavenLocal
```

详见 [docs/guides/getting-started.md](docs/guides/getting-started.md)。

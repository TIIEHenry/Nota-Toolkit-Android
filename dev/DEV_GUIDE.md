---
title: "开发速查手册"
type: concept
status: accepted
phase: N/A
updated: 2026-06-19
summary: "日常开发速查：模块地图、常用命令、工作流"
---

# Nota-Toolkit 开发速查手册

> 快速定位模块、命令、工作流。深度文档见 [docs/INDEX.md](../docs/INDEX.md)。

---

## 1. 项目现状速览

| 项 | 状态 |
|---|------|
| Active 模块 | 7（见 settings.gradle） |
| Legacy 模块 | 4（未 include） |
| 构建 | `./gradlew assembleRelease` — **9 模块**通过 |
| 文档系统 | Phase 1–6 ✅ 全部归档 |
| 测试 | 无单元测试 / 仪器测试 |

---

## 2. 模块地图

```
Nota-Toolkit-Android/
├── android-common              核心整合库 (nota.*)
├── android-widget-loadingview  LoadingView
├── android-widget-searchview   SearchLayout
├── android-widget-treeview     TreeViewAdapter
├── android-widget-flowtag      FlowTagLayout
├── io-filej                    Filej / Zipl
├── io-encodehelper             EncodeHelper (cpdetector)
│
└── [Legacy]
    ├── android-base            AbstractRecyclerAdapter
    ├── android-fragment        Fragment Pager Adapters
    ├── android-ktx             Kotlin extensions
    └── io-encodedetector       ICU CharsetDetector (dup)
```

---

## 3. 常用命令

```bash
# 构建所有 Active 模块
./gradlew assembleRelease
./scripts/verify-build.sh

# 发布到 mavenLocal
./gradlew publishToMavenLocal

# 发布单个模块
./gradlew :android-common:publishModulePublicationToMavenLocal

# 文档索引与健康检查
./scripts/generate-docs-index.sh
python3 scripts/check-docs-health.py
```

---

## 4. 开发工作流

```
AGENTS.md → DOCUMENTATION.md → status.md
    ↓
roadmap/active/phaseN_*.md（取 checkbox）
    ↓
编码 → 更新 status.md + 勾选 checkbox
    ↓
commit（方案与实施分开）
```

---

## 5. 技术栈

| 项 | 值 |
|----|-----|
| compileSdk / targetSdk | 36 |
| minSdk | 21 |
| Java | 17 |
| Kotlin | 2.3 |
| Gradle | 9.1 |
| AGP | 9.0.0 |

---

## 6. 已知缺口

- 无 Sample App 模块
- 无自动化测试
- Legacy 与 Active API 重复（Charset、SimpleTextWatcher、Adapter）
- Legacy `tiiehenry.*` Java 包迁移（Phase 6）
- 无 Sample App / 无自动化测试

---

## 相关文档

- [docs/architecture/module-governance.md](../docs/architecture/module-governance.md)
- [docs/guides/getting-started.md](../docs/guides/getting-started.md)

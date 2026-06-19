# Nota-Toolkit-Android — Android 工具库集合

多模块 Android Library 工具包，提供 Adapter 框架、自定义 Widget、I/O 与编码检测、Kotlin 扩展等可复用能力。各模块独立发布为 Maven AAR（`tiiehenry.nota.toolkit:<artifactId>:<version>`）。

> **新会话必读**：`AGENTS.md` → [`docs/DOCUMENTATION.md`](docs/DOCUMENTATION.md) → [`dev/progress/status.md`](dev/progress/status.md)

---

## 1. 源码目录

| 目录 | 说明 |
|------|------|
| `android-common/` | 核心整合库（`nota.*` 包）：Adapter、View、LiveData、Crypto、Reflection、Charset |
| `android-widget-*/` | 独立自定义 View 模块（Loading、Search、Tree、FlowTag） |
| `io-filej/`、`io-encodehelper/` | I/O 与编码检测 |
| `android-base/`、`android-fragment/` | **Legacy** Adapter（已纳入 `settings.gradle`，v2.0） |
| `android-ktx/` | **Legacy** Kotlin 扩展（未纳入构建） |
| `io-encodedetector/` | **Legacy** ICU 编码检测（与 `android-common` 重复） |
| `docs/` | 稳定知识库（架构、ADR、指南） |
| `dev/` | 开发追踪（roadmap、进度、计划、归档） |
| `gradle/maven-publish.gradle` | 统一 AAR + sources 发布配置 |

---

## 2. 开发规范

> 完整规则见 [`docs/DOCUMENTATION.md`](docs/DOCUMENTATION.md)。

### 开发流程

```
1. 方案   → docs/architecture/<topic>.md 或 docs/architecture/modules/<module>.md
2. ADR    → docs/decisions/NNN-<topic>.md（架构取舍时）
3. 任务   → dev/roadmap/active/phaseN_*.md
   ── commit ── 方案阶段（仅文档，无实现代码）
4. 编码   → 按 checkbox 实施
5. 进度   → dev/progress/status.md + 勾选 roadmap
   ── commit ── 实施阶段（代码 + 行动层文档）
```

**提交前文档门禁**（[规则 3a](docs/DOCUMENTATION.md#规则-3a提交前文档门禁commit-前必做)）：含代码的 commit 前更新 `status.md`、roadmap checkbox、改动过的 `.md`；公开 API 或行为变更时同步 `docs/architecture/`。

**方案 / 实施 commit 必须分开**。

**禁止**：跳过方案直接改公开 API（trivial 除外）| 无 ADR 做模块级架构决策 | 代码 merge 后文档「下次再补」

**方案质量把关**：新建/大幅修订方案、ADR、roadmap → [`.cursor/rules/architecture-review.mdc`](.cursor/rules/architecture-review.mdc)。

### 文档维护规则

| # | 规则 | 触发时机 |
|---|------|----------|
| 1 | **编码前查阅文档**：`docs/architecture/` + `dev/progress/status.md` | 每次编码任务 |
| 2 | **编码后更新进度**：`dev/progress/status.md`，勾选 roadmap | 每次编码会话 |
| 3 | **新设计决策 → ADR**：`docs/decisions/NNN-xxx.md` | 模块边界、API 契约、迁移策略 |
| 4 | **文档过时 → 归档**：移入 `dev/archive/`，不删除 | 内容被取代 |
| 5 | **AGENTS.md 最高权威**：文档冲突时以本文件为准 | 文档冲突 |
| 6 | **frontmatter**（`AGENTS.md`/`CLAUDE.md` 豁免）：新建必有；编辑更新 `updated` | 新建或编辑文档 |
| 7 | **不重复创建，不删除文档** | 始终 |
| 8 | **提交前门禁**：规则 3a；结构变更后 `check-docs-health.py` | 每次实施 commit 前 |

### 文档类型与位置

| type | 位置 | 含义 |
|------|------|------|
| `architecture` | `docs/architecture/` | 模块/子系统设计 |
| `decision` | `docs/decisions/` | 架构决策记录（ADR） |
| `concept` | `docs/architecture/concepts/` | 跨模块约定 |
| `reference` | `docs/reference/` | 外部参考、对比分析 |
| `guide` | `docs/guides/` | 操作指南 |
| `roadmap` | `dev/roadmap/` | 阶段任务清单 |
| `plan` | `dev/plans/` | 实施计划 |
| `progress` | `dev/progress/` | 进度追踪 |

---

## 3. 模块状态

### Active（`settings.gradle` 已 include）

| Module | Version | Namespace | 职责 |
|--------|---------|-----------|------|
| `android-common` | 1.0 | `nota.android` | 核心工具库 |
| `android-widget-loadingview` | 1.1 | `nota.android.widget.loadingview` | 加载动画 View |
| `android-widget-searchview` | 1.1 | `nota.android.widget.searchview` | 搜索栏 |
| `android-widget-treeview` | 1.1 | `nota.android.widget.treeview` | 树形 RecyclerView |
| `android-widget-flowtag` | 1.0 | `nota.android.widget.flowtag` | 流式标签布局 |
| `io-filej` | 1.5 | `nota.io.filej` | 增强 File API |
| `io-encodehelper` | 1.2 | `nota.io.encodehelper` | cpdetector 编码检测 |
| `android-base` | 2.0 | `nota.android.base` | Legacy Adapter 框架 |
| `android-fragment` | 2.0 | `nota.android.fragment` | Legacy Fragment Pager |

### Legacy（未纳入 `settings.gradle` 构建）

| Module | Version | 说明 |
|--------|---------|------|
| `android-ktx` | 1.0 | 纯 Kotlin 扩展 |
| `io-encodedetector` | 67.1 | ICU CharsetDetector 重复实现 |

> 模块选型与迁移路径见 [`docs/architecture/module-governance.md`](docs/architecture/module-governance.md)。

---

## 4. 技术约束

| 项 | 约束 |
|----|------|
| compileSdk / targetSdk | 36 |
| minSdk | 21 |
| Java | 17 |
| Kotlin | 2.3（`android-common` 等模块显式声明） |
| AndroidX | 是（`android.useAndroidX=true`） |
| ViewBinding | `android-common` 启用 |
| 发布 | `publishModulePublicationToMavenLocal` → `mavenLocal()` |
| 坐标 | `group = tiiehenry.nota.toolkit`，`artifactId = 模块名` |

### 包名体系

- **`nota.*`** — 新整合代码（优先依赖）
- **`tiiehenry.*`** — Legacy 模块与部分 I/O 包

新代码一律使用 `nota.*`；Legacy 模块仅在维护 bugfix 时修改，新功能写入 `android-common` 或新模块。

---

## 5. 架构原则

1. **模块独立发布**：每个 library 模块可单独 `publishToMavenLocal`，避免隐式跨模块 compile 依赖（Legacy 除外）。
2. **Adapter 分层**：Legacy `AbstractRecyclerAdapter`（Java）与 `VBRecyclerAdapter`（ViewBinding/Kotlin）并存；新 UI 优先 ViewBinding 方案。
3. **Widget 模块化**：自定义 View 独立成模块，自带 `res/`，不耦合 `android-common`。
4. **I/O 与编码**：`Filej` 增强文件操作；编码检测有三套实现，文档必须说明选型（见 [`docs/guides/charset-detection.md`](docs/guides/charset-detection.md)）。
5. **Kotlin 优先**：新 API 用 Kotlin；Legacy Java 保持原样，不强制重写。
6. **无 Sample App**：消费方自行集成；文档与 guide 承担示例职责。

---

## 6. Agent 检索速查

| 我想… | 去这里 |
|-------|--------|
| 项目全貌 | 本文件 |
| 文档维护规则 | `docs/DOCUMENTATION.md` |
| 当前状态 | `dev/progress/status.md` |
| 模块地图 | `docs/architecture/module-governance.md` |
| 模块详情 | `docs/architecture/modules/` |
| 架构决策 | `docs/decisions/` |
| 使用指南 | `docs/guides/` |
| 活跃任务 | `dev/roadmap/active/` |
| 开发命令 | `dev/DEV_GUIDE.md` |
| 术语 | `docs/glossary.md` |
| 完整索引 | `docs/INDEX.md`（脚本生成） |

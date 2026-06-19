---
title: "Documentation System — LLM Maintenance Rules"
type: concept
status: accepted
phase: N/A
updated: 2026-06-19
summary: "Nota-Toolkit 文档维护规则：目录、frontmatter、提交门禁、ADR/迭代分流"
---

# Nota-Toolkit 文档系统维护规则

> **受众**：LLM / AI 助手。人类开发者亦可参考。

## 1. 目录架构

```
docs/                         # 知识库（稳定）
├── DOCUMENTATION.md          # ← 本文件
├── DOC-SPEC.md               # 分类与模板
├── README.md                 # 人类导航
├── INDEX.md                  # 脚本生成，禁止手改
├── glossary.md               # 术语 SSOT
├── architecture/
│   ├── module-governance.md  # 模块治理总览
│   ├── concepts/             # 跨模块约定
│   └── modules/              # 各模块设计文档
├── decisions/                # ADR
├── reference/                # 外部参考
├── guides/                   # 操作指南
└── templates/

dev/                          # 行动层（频繁更新）
├── progress/status.md        # 当前迭代状态
├── roadmap/
│   ├── INDEX.md
│   ├── active/               # 进行中 phase
│   └── archive/              # 已完成 phase
├── parallel/
│   ├── active/
│   └── archive/
├── iterations/<module>/      # 实现日志（非 ADR）
├── plans/
└── archive/
```

| 原则 | 说明 |
|------|------|
| 稳定与动态分离 | `docs/` = 知识；`dev/` = 行动 |
| 单一事实源 | 概念只定义一次，链接引用 |
| ADR 在 docs | `docs/decisions/`；实现过程在 `dev/iterations/` |
| 只归档不删除 | 过时 → `dev/archive/` |
| AGENTS.md 最高权威 | 冲突时以 AGENTS.md 为准 |

## 2. Frontmatter

```yaml
---
title: "标题"
type: architecture | concept | decision | reference | guide | roadmap | plan | progress | index | verification
status: draft | review | accepted | implemented | completed | archived | active | in_progress
phase: N/A | <数字>
updated: YYYY-MM-DD
summary: "一行描述"
---
```

**豁免**：`AGENTS.md`、`CLAUDE.md` 不强制 frontmatter。

## 3. LLM 维护规则

### 规则 1：新会话三步走

1. `AGENTS.md` — 项目全貌
2. 本文件 — 维护规则
3. `dev/progress/status.md` — 当前状态

### 规则 2：编码前

查 `docs/architecture/`、`docs/decisions/`、`dev/progress/status.md`；活跃任务见 `dev/roadmap/active/`。

### 规则 3：编码后

更新 `status.md`（**仅 Snapshot + Recent Sessions**，≤200 行）；勾选 roadmap `- [x]`。

**status.md 结构**：

| 区块 | 内容 | 上限 |
|------|------|------|
| Snapshot | 活跃 Phase、阻塞、验证基线 | ~40 行 |
| Recent Sessions | 最近 12 条会话摘要 | ~80 行 |
| 历史 | → `dev/progress/archive/` | 不写入 status |

### 规则 3a：提交前文档门禁（commit 前必做）

| 层级 | 位置 | 何时 |
|------|------|------|
| 行动层 | `dev/` | 含代码的 commit 前必做（trivial bugfix 除外） |
| 知识层 | `docs/` | 公开 API / 行为契约变更时 |

**行动层 checklist**：`status.md` 已更新；roadmap checkbox 已勾选；改动过的 `.md` 的 `updated`/`status` 已对齐。

**知识层**：公开 API 或行为变了 → 更新对应 `docs/architecture/`（1–2 份即可）；纯 refactor 通常不必。

**trivial bugfix**：单行 typo、无行为变化 → 可仅提交代码。

### 规则 4：ADR vs 阶段日志

| 写什么 | 放哪里 |
|--------|--------|
| 模块边界、API 契约、迁移策略 | `docs/decisions/NNN-topic.md` |
| phase 踩坑、验证、实现细节 | `dev/iterations/<module>/` |

ADR 模板：`docs/templates/decision-template.md`。`accepted` 后不改；推翻须新 ADR。

### 规则 5：roadmap 生命周期

- **新 phase** → `dev/roadmap/active/phaseN_*.md`
- **完成后** → `git mv` 到 `dev/roadmap/archive/`，frontmatter `status: archived`
- **索引** → `dev/roadmap/INDEX.md` 列出 active / archive 分区

### 规则 6：并行看板

触发（任一）：5+ 模块并行改动 / 多 Agent / 跨 2+ 会话。

- 大任务 → `dev/parallel/active/<topic>.md`，完成后 `git mv` 至 `archive/`
- 轻量协调 → `dev/progress/parallel-board.md`

### 规则 7：归档

移入 `dev/archive/`；`status: archived`；运行 `./scripts/generate-docs-index.sh`。

### 规则 8：Frontmatter

新建文件必有完整 frontmatter；每次编辑更新 `updated`。

### 规则 9：不重复

先搜索已有文档；术语引用 `docs/glossary.md`。

### 规则 10：索引

结构变更后：`./scripts/generate-docs-index.sh` + `python3 scripts/check-docs-health.py`。

### 规则 11：不额外建 README

除非用户明确要求（模块级 README 由 roadmap 驱动，非默认）。

### 规则 12：方案与实施 commit 分离

- **方案 commit**：plan / roadmap / ADR，无实现代码
- **实施 commit**：代码 + 行动层 + 按需知识层
- 两类 **必须分开**；提交前均满足规则 3a

### 规则 13：会话结束自检

- [ ] 规则 3a 已满足
- [ ] roadmap checkbox 已勾选
- [ ] 改动过的 `.md` 已更新 `updated`
- [ ] 结构变更后已跑健康检查

## 4. 检索速查

| 我想… | 去这里 |
|-------|--------|
| 项目全貌 | `AGENTS.md` |
| 当前状态 | `dev/progress/status.md` |
| 模块设计 | `docs/architecture/modules/<module>.md` |
| 模块治理 | `docs/architecture/module-governance.md` |
| 架构决策 | `docs/decisions/` |
| 实现过程 | `dev/iterations/` |
| 活跃 phase | `dev/roadmap/active/` |
| commit 前对齐什么 | 规则 3a |
| 文档健康 | `python3 scripts/check-docs-health.py` |

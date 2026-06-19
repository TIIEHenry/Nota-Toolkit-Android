---
title: "文档索引"
type: index
status: accepted
phase: N/A
updated: 2026-06-19
summary: "docs/ + dev/ 完整导航索引（自动生成）"
---

# Nota-Toolkit 文档索引

> **本文件由脚本自动生成，请勿手动编辑。**

---

## 文档系统

| 文件 | 说明 |
|------|------|
| [DOCUMENTATION.md](DOCUMENTATION.md) | **LLM 维护规则** |
| [DOC-SPEC.md](DOC-SPEC.md) | **文档系统规范** |
| [glossary.md](glossary.md) | **术语表** |
| [dev/DEV_GUIDE.md](../dev/DEV_GUIDE.md) | **开发速查手册** |

---

## 模块治理（`docs/architecture/`）

| 文档 | 内容 |
|------|------|
| [module-governance.md](architecture/module-governance.md) | --- |

### 跨模块概念（`concepts/`）

| 文档 | 内容 |
|------|------|
| [adapter-framework.md](architecture/concepts/adapter-framework.md) | --- |
| [namespace-alignment.md](architecture/concepts/namespace-alignment.md) | --- |
| [package-naming.md](architecture/concepts/package-naming.md) | --- |

### 模块设计（`modules/`）

| 文档 | 内容 |
|------|------|
| [android-common.md](architecture/modules/android-common.md) | --- |
| [io.md](architecture/modules/io.md) | --- |
| [legacy-adapters.md](architecture/modules/legacy-adapters.md) | --- |
| [widgets.md](architecture/modules/widgets.md) | --- |

---

## 架构决策（`docs/decisions/`）

| 文档 | 内容 |
|------|------|
| [001-nota-package-consolidation.md](decisions/001-nota-package-consolidation.md) | --- |
| [002-namespace-java-package-alignment.md](decisions/002-namespace-java-package-alignment.md) | --- |
| [003-duplicate-api-consolidation.md](decisions/003-duplicate-api-consolidation.md) | --- |
| [004-loadingview-package-alignment.md](decisions/004-loadingview-package-alignment.md) | --- |
| [005-legacy-adapter-package-migration.md](decisions/005-legacy-adapter-package-migration.md) | --- |

---

## 指南（`docs/guides/`）

| 文档 | 内容 |
|------|------|
| [charset-detection.md](guides/charset-detection.md) | --- |
| [getting-started.md](guides/getting-started.md) | --- |
| [legacy-package-migration.md](guides/legacy-package-migration.md) | --- |
| [publish-to-maven-local.md](guides/publish-to-maven-local.md) | --- |
| [recyclerview-viewbinding.md](guides/recyclerview-viewbinding.md) | --- |
| [widget-flowtag.md](guides/widget-flowtag.md) | --- |
| [widget-loadingview.md](guides/widget-loadingview.md) | --- |
| [widget-searchview.md](guides/widget-searchview.md) | --- |
| [widget-treeview.md](guides/widget-treeview.md) | --- |

---

## 开发追踪（`dev/`）

| 目录 | 说明 |
|------|------|
| [dev/README.md](../dev/README.md) | dev/ 使用说明 |
| [dev/DEV_GUIDE.md](../dev/DEV_GUIDE.md) | 开发速查手册 |

### 路线图（`dev/roadmap/`）

| Phase | 文档 | 说明 | 状态 |
|-------|------|------|------|
| 1 | [phase1_doc-system-bootstrap.md](../dev/roadmap/archive/phase1_doc-system-bootstrap.md) | --- |  |
| 2 | [phase2_namespace-alignment.md](../dev/roadmap/archive/phase2_namespace-alignment.md) | --- |  |
| 4 | [phase4_widget-guides.md](../dev/roadmap/archive/phase4_widget-guides.md) | --- |  |
| 5 | [phase5_build-and-package-cleanup.md](../dev/roadmap/archive/phase5_build-and-package-cleanup.md) | --- |  |
| 6 | [phase6_legacy-adapter-migration.md](../dev/roadmap/archive/phase6_legacy-adapter-migration.md) | --- |  |

### 进度追踪（`dev/progress/`）

| 文档 | 内容 |
|------|------|
| [status.md](../dev/progress/status.md) | — |

---

## 按阅读路径

### 新人入门
1. [dev/DEV_GUIDE.md](../dev/DEV_GUIDE.md) — 开发速查
2. [DOCUMENTATION.md](DOCUMENTATION.md) — 文档规则
3. [architecture/module-governance.md](architecture/module-governance.md) — 模块地图
4. [guides/getting-started.md](guides/getting-started.md) — 快速上手

### 若关注 Adapter
1. [architecture/concepts/adapter-framework.md](architecture/concepts/adapter-framework.md)
2. [architecture/modules/android-common.md](architecture/modules/android-common.md)

### 若关注 Widget
1. [architecture/modules/widgets.md](architecture/modules/widgets.md)

*索引生成日期：2026-06-19*

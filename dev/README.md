---
title: "dev/ 使用说明"
type: concept
status: accepted
phase: N/A
updated: 2026-06-19
summary: "dev/ 行动层：roadmap active/archive、status 快照、迭代归档"
---

# dev/ — 开发追踪

> 频繁变动；稳定知识在 `docs/`。维护规则见 [DOCUMENTATION.md](../docs/DOCUMENTATION.md)。

## 子目录

| 目录 | 用途 |
|------|------|
| `progress/status.md` | 当前迭代状态（每次实施 commit 前更新） |
| `roadmap/INDEX.md` | Phase 索引（active / archive 分区） |
| `roadmap/active/` | 进行中 phase |
| `roadmap/archive/` | 已完成 phase |
| `progress/archive/` | status 历史 Session |
| `parallel/active/` → `archive/` | 大任务并行看板 |
| `iterations/<module>/` | 实现日志（非 ADR） |
| `plans/` | 实施计划 |
| `verification/` | 构建/设备验证报告 |
| `archive/` | 过时文档 |

## 流程

1. 读 `status.md`（Snapshot）+ [`roadmap/active/`](roadmap/active/)
2. 编码 → 勾选 checkbox → [规则 3a](../docs/DOCUMENTATION.md#规则-3a提交前文档门禁commit-前必做) → commit

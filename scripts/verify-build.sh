#!/bin/bash
# 验证 Active 模块 Release 构建
# 用法: ./scripts/verify-build.sh

set -e
REPO_ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$REPO_ROOT"

echo "=== Nota-Toolkit 构建验证 ==="
./gradlew assembleRelease --quiet
echo "=== BUILD SUCCESSFUL ==="

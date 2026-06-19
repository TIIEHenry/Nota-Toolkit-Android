#!/bin/bash
# 文档索引生成脚本（Nota-Toolkit-Android 适配版）
# 用法: ./scripts/generate-docs-index.sh

set -e

REPO_ROOT="$(cd "$(dirname "$0")/.." && pwd)"
DOCS_DIR="$REPO_ROOT/docs"
DEV_DIR="$REPO_ROOT/dev"
INDEX_FILE="$DOCS_DIR/INDEX.md"

extract_frontmatter_field() {
    local file=$1
    local field=$2
    sed -n '/^---$/,/^---$/p' "$file" 2>/dev/null \
        | grep "^${field}:" \
        | head -1 \
        | sed "s/^${field}: *//; s/^\"//; s/\"$//"
}

get_title() {
    local file=$1
    local title
    title=$(extract_frontmatter_field "$file" "title")
    if [ -z "$title" ]; then
        title=$(head -1 "$file" 2>/dev/null | sed 's/^# *//')
    fi
    echo "$title"
}

get_summary() {
    local file=$1
    extract_frontmatter_field "$file" "summary"
}

generate_section() {
    local label=$1
    local dir=$2
    local prefix=$3
    echo "## $label"
    echo ""
    echo "| 文档 | 内容 |"
    echo "|------|------|"
    for f in "$dir"/*.md; do
        [ -f "$f" ] || continue
        local name=$(basename "$f")
        [ "$name" = "README.md" ] && continue
        [ "$name" = "INDEX.md" ] && continue
        [ "$name" = "DOCUMENTATION.md" ] && continue
        [ "$name" = "DOC-SPEC.md" ] && continue
        local title=$(get_title "$f")
        local summary=$(get_summary "$f")
        echo "| [$name](${prefix}${name}) | ${summary:-$title} |"
    done
}

generate_subdir_section() {
    local label=$1
    local dir=$2
    local prefix=$3
    [ -d "$dir" ] || return
    echo ""
    echo "### $label"
    echo ""
    echo "| 文档 | 内容 |"
    echo "|------|------|"
    for f in "$dir"/*.md; do
        [ -f "$f" ] || continue
        local name=$(basename "$f")
        [ "$name" = "README.md" ] && continue
        local title=$(get_title "$f")
        local summary=$(get_summary "$f")
        echo "| [$name](${prefix}${name}) | ${summary:-$title} |"
    done
}

generate_roadmap_section() {
    echo ""
    echo "---"
    echo ""
    echo "## 开发追踪（\`dev/\`）"
    echo ""
    echo "| 目录 | 说明 |"
    echo "|------|------|"
    echo "| [dev/README.md](../dev/README.md) | dev/ 使用说明 |"
    echo "| [dev/DEV_GUIDE.md](../dev/DEV_GUIDE.md) | 开发速查手册 |"
    echo ""
    echo "### 路线图（\`dev/roadmap/\`）"
    echo ""
    echo "| Phase | 文档 | 说明 | 状态 |"
    echo "|-------|------|------|------|"

    for subdir in active archive; do
        local rdir="$DEV_DIR/roadmap/$subdir"
        [ -d "$rdir" ] || continue
        for f in "$rdir"/phase*.md; do
            [ -f "$f" ] || continue
            local name=$(basename "$f")
            local title=$(get_title "$f")
            local status=$(extract_frontmatter_field "$f" "status")
            local phase_num=$(echo "$name" | grep -oP 'phase\K\d+' || echo "—")
            local short_desc=$(echo "$title" | sed 's/^Phase [0-9]*[:：] *//')
            local display_status=""
            case "$status" in
                complete|completed|archived) display_status="✅" ;;
                in_progress|in-progress) display_status="🔄" ;;
                *) display_status="" ;;
            esac
            echo "| $phase_num | [$name](../dev/roadmap/$subdir/$name) | $short_desc | $display_status |"
        done
    done
}

echo "=== 生成文档索引 ==="

TMPFILE=$(mktemp)
trap "rm -f $TMPFILE" EXIT

{
    echo "---"
    echo "title: \"文档索引\""
    echo "type: index"
    echo "status: accepted"
    echo "phase: N/A"
    echo "updated: $(date +%Y-%m-%d)"
    echo "summary: \"docs/ + dev/ 完整导航索引（自动生成）\""
    echo "---"
    echo ""
    echo "# Nota-Toolkit 文档索引"
    echo ""
    echo "> **本文件由脚本自动生成，请勿手动编辑。**"
    echo ""
    echo "---"
    echo ""
    echo "## 文档系统"
    echo ""
    echo "| 文件 | 说明 |"
    echo "|------|------|"
    echo "| [DOCUMENTATION.md](DOCUMENTATION.md) | **LLM 维护规则** |"
    echo "| [DOC-SPEC.md](DOC-SPEC.md) | **文档系统规范** |"
    echo "| [glossary.md](glossary.md) | **术语表** |"
    echo "| [dev/DEV_GUIDE.md](../dev/DEV_GUIDE.md) | **开发速查手册** |"
    echo ""
    echo "---"
    echo ""

    generate_section "模块治理（\`docs/architecture/\`）" "$DOCS_DIR/architecture" "architecture/"
    generate_subdir_section "跨模块概念（\`concepts/\`）" "$DOCS_DIR/architecture/concepts" "architecture/concepts/"
    generate_subdir_section "模块设计（\`modules/\`）" "$DOCS_DIR/architecture/modules" "architecture/modules/"

    echo ""
    echo "---"
    echo ""
    generate_section "架构决策（\`docs/decisions/\`）" "$DOCS_DIR/decisions" "decisions/"

    if [ -d "$DOCS_DIR/reference" ]; then
        echo ""
        echo "---"
        echo ""
        generate_section "参考资料（\`docs/reference/\`）" "$DOCS_DIR/reference" "reference/"
    fi

    echo ""
    echo "---"
    echo ""
    generate_section "指南（\`docs/guides/\`）" "$DOCS_DIR/guides" "guides/"

    generate_roadmap_section

    if [ -d "$DEV_DIR/plans" ]; then
        echo ""
        echo "### 实施计划（\`dev/plans/\`）"
        echo ""
        echo "| 文档 | 内容 |"
        echo "|------|------|"
        for f in "$DEV_DIR/plans"/*.md; do
            [ -f "$f" ] || continue
            name=$(basename "$f")
            summary=$(get_summary "$f")
            echo "| [$name](../dev/plans/$name) | ${summary:-—} |"
        done
    fi

    echo ""
    echo "### 进度追踪（\`dev/progress/\`）"
    echo ""
    echo "| 文档 | 内容 |"
    echo "|------|------|"
    for f in "$DEV_DIR/progress"/*.md; do
        [ -f "$f" ] || continue
        name=$(basename "$f")
        summary=$(get_summary "$f")
        echo "| [$name](../dev/progress/$name) | ${summary:-—} |"
    done

    echo ""
    echo "---"
    echo ""
    echo "## 按阅读路径"
    echo ""
    echo "### 新人入门"
    echo "1. [dev/DEV_GUIDE.md](../dev/DEV_GUIDE.md) — 开发速查"
    echo "2. [DOCUMENTATION.md](DOCUMENTATION.md) — 文档规则"
    echo "3. [architecture/module-governance.md](architecture/module-governance.md) — 模块地图"
    echo "4. [guides/getting-started.md](guides/getting-started.md) — 快速上手"
    echo ""
    echo "### 若关注 Adapter"
    echo "1. [architecture/concepts/adapter-framework.md](architecture/concepts/adapter-framework.md)"
    echo "2. [architecture/modules/android-common.md](architecture/modules/android-common.md)"
    echo ""
    echo "### 若关注 Widget"
    echo "1. [architecture/modules/widgets.md](architecture/modules/widgets.md)"
    echo ""
    echo "*索引生成日期：$(date +%Y-%m-%d)*"
} > "$TMPFILE"

cp "$TMPFILE" "$INDEX_FILE"
echo "=== 索引生成完成: $INDEX_FILE ==="

package nota.android.widget.recyclerview.expandable

open class ExpandableItem(val type: Int) {
    companion object {
        const val TYPE_PARENT = 0
        const val TYPE_CHILD = 1
    }
}

data class ExpandableParentItem<D, C>(
    val item: D,
    var isExpanded: Boolean = false, // 展开状态
    val children: MutableList<ExpandableChildItem<D, C>> = mutableListOf()
) : ExpandableItem(TYPE_PARENT) {
    val hasChildren get() = children.isNotEmpty()
}

data class ExpandableChildItem<D, C>(
    val parentItem: D,
    val item: C,
) : ExpandableItem(TYPE_CHILD)
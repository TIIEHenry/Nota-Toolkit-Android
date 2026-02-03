package nota.android.widget.recyclerview.expandable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class ExpandableListAdapter<D, C>(
    val listener: OnItemClickListener<D, C>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener<D, C> {
        fun onToggle(isExpanded: Boolean)
        fun onItemClick(item: ExpandableParentItem<D, C>): Boolean
        fun onChildClick(item: ExpandableChildItem<D, C>)
        fun onItemLongClick(item: ExpandableParentItem<D, C>): Boolean
        fun onChildItemLongClick(item: ExpandableChildItem<D, C>): Boolean
    }

    val itemList: MutableList<ExpandableItem> = mutableListOf()

    abstract fun createParentViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): IParentViewHolder<D, C>

    abstract fun createChildViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): IChildViewHolder<D, C>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ExpandableItem.TYPE_PARENT -> {
                createParentViewHolder(inflater, parent, viewType)
            }

            ExpandableItem.TYPE_CHILD -> {
                createChildViewHolder(inflater, parent, viewType)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemList[position]
        when (holder) {
            is IParentViewHolder<*, *> -> {
                val parentItem = item as ExpandableParentItem<D, C>
                (holder as IParentViewHolder<D, C>).bind(this, parentItem)
            }

            is IChildViewHolder<*, *> -> {
                val childItem = item as ExpandableChildItem<D, C>
                (holder as IChildViewHolder<D, C>).bind(this, childItem)
            }
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int = itemList[position].type

    // ViewHolder for Parent Items
    abstract class IParentViewHolder<D, C>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            adapter: ExpandableListAdapter<D, C>,
            parentItem: ExpandableParentItem<D, C>
        ) {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    adapter.toggleExpansion(position, parentItem)
                    onToggle(parentItem, parentItem.isExpanded)
                    adapter.notifyItemChanged(position)
                }
            }
            itemView.setOnLongClickListener {
                adapter.listener.onItemLongClick(parentItem)
            }
            onBind(adapter, parentItem)
        }

        /**
         * onBind内设置onItemClick
         */
        abstract fun onBind(
            adapter: ExpandableListAdapter<D, C>,
            parentItem: ExpandableParentItem<D, C>
        )

        abstract fun onToggle(parentItem: ExpandableParentItem<D, C>, isExpanded: Boolean)
    }

    // ViewHolder for Child Items
    abstract class IChildViewHolder<D, C>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(adapter: ExpandableListAdapter<D, C>, childItem: ExpandableChildItem<D, C>) {
            onBind(childItem)
            itemView.setOnClickListener {
                adapter.listener.onChildClick(childItem)
            }
            itemView.setOnLongClickListener {
                adapter.listener.onChildItemLongClick(childItem) ?: false
            }
        }

        abstract fun onBind(childItem: ExpandableChildItem<D, C>)
    }

    // 展开/折叠逻辑
    internal fun toggleExpansion(position: Int, parentItem: ExpandableParentItem<D, C>) {
        parentItem.isExpanded = !parentItem.isExpanded
        if (parentItem.isExpanded) {
            listener.onToggle(true)
            // 插入子条目
            val childIndex = itemList.indexOf(parentItem) + 1
            itemList.addAll(childIndex, parentItem.children)
            notifyItemRangeInserted(childIndex, parentItem.children.size)
        } else {
            listener.onToggle(false)
            // 移除子条目
            val childIndex = itemList.indexOf(parentItem) + 1
            itemList.removeAll(parentItem.children)
            notifyItemRangeRemoved(childIndex, parentItem.children.size)
        }
    }
}

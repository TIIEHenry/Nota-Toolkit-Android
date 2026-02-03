package nota.android.widget.recyclerview

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

interface VBRecyclerViewListener<VB : ViewBinding, A : RecyclerView.Adapter<ViewBindingHolder<VB>>> {
    fun onItemClick(adapter: A, holder: ViewBindingHolder<VB>, position: Int)
    fun onItemLongClick(adapter: A, holder: ViewBindingHolder<VB>, position: Int): Boolean
}

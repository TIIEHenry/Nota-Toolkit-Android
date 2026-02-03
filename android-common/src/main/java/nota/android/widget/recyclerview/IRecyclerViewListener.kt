package nota.android.widget.recyclerview

import androidx.recyclerview.widget.RecyclerView

interface IRecyclerViewListener<D : RecyclerView.ViewHolder, A : RecyclerView.Adapter<D>> {
    fun onItemClick(adapter: A, position: Int)
    fun onItemLongClick(adapter: A, position: Int): Boolean
}

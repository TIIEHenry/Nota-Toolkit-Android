package nota.android.widget.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

abstract class VBListRecyclerAdapter<D, VB : ViewBinding>(
    val vbFactory: (LayoutInflater, ViewGroup, Boolean) -> VB,
    diffCallback: DiffUtil.ItemCallback<D>
) : ListAdapter<D, ViewBindingHolder<VB>>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingHolder<VB> {
        return ViewBindingHolder(vbFactory(LayoutInflater.from(parent.context), parent, false))
    }
}

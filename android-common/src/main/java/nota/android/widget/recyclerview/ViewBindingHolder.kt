package nota.android.widget.recyclerview

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class ViewBindingHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root) {
}
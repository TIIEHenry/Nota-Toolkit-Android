package nota.android.view.spinner

import androidx.viewbinding.ViewBinding

class SpinnerViewHolder<VB : ViewBinding>(val binding: VB) {
    @JvmField
    val itemView = binding.root
}

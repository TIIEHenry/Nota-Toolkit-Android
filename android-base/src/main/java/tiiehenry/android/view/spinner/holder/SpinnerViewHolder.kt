package tiiehenry.android.view.spinner.holder

import android.view.View
import androidx.viewbinding.ViewBinding

class SpinnerViewHolder<VB : ViewBinding>(val binding: VB) {
    @JvmField
    val itemView = binding.root
}

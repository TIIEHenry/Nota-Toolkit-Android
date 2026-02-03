package nota.android.widget.recyclerview.touch

interface ItemTouchHelperAdapter {

    fun isLongPressDragEnabled(): Boolean {
        return true
    }

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemDismiss(position: Int)

}
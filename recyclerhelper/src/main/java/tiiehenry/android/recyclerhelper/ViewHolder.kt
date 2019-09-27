package tiiehenry.android.recyclerhelper


import android.util.SparseArray
import android.view.View

import androidx.recyclerview.widget.RecyclerView

class ViewHolder<T : Any>(val rootView: View) : RecyclerView.ViewHolder(rootView),
    View.OnLongClickListener, View.OnClickListener {

    var onItemClickListener: (View, T, Int) -> Unit = { rootView, data, pos -> }
    var onItemLongClickListener: (View, T, Int) -> Boolean =
        { rootView, data, pos -> false }

    // SparseArray 比 HashMap 更省内存，在某些条件下性能更好，只能存储 key 为 int 类型的数据，
    // 用来存放 View 以减少 findViewById 的次数
    private val viewSparseArray = SparseArray<View?>()
    lateinit var data: T


    init {
        rootView.setOnClickListener(this)
        rootView.setOnLongClickListener(this)
    }

    fun getView(viewId: Int): View? {
        var view = viewSparseArray.get(viewId)
        if (view == null) {
            view = rootView.findViewById(viewId)
            viewSparseArray.put(viewId, view)
        }
        return view
    }


    override fun onClick(rootView: View) {
        onItemClickListener.invoke(rootView, data, adapterPosition)
    }

    override fun onLongClick(rootView: View): Boolean {
        return onItemLongClickListener.invoke(rootView, data, adapterPosition)
    }


}

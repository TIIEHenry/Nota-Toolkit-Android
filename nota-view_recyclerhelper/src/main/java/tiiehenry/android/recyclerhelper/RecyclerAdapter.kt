package tiiehenry.android.recyclerhelper

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


abstract class RecyclerAdapter<T:Any>(list: MutableList<T>) : RecyclerView.Adapter<ViewHolder<T>>() {


    var dataList = list
        set(data) {
            dataList.clear()
            dataList.addAll(data)
            notifyDataSetChanged()
            dataList
        }


    private var onItemClickListener: (View, T, Int) -> Unit = { rootView, data, pos -> }
    private var onItemLongClickListener: (View, T, Int) -> Boolean =
        { rootView, data, pos -> false }



    abstract override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ViewHolder<T>

    override fun onBindViewHolder(holder: ViewHolder<T>, pos: Int) {
        val data = dataList[pos]
        holder.data = data
        holder.onItemClickListener=onItemClickListener
        holder.onItemLongClickListener=onItemLongClickListener
        bindData(holder, data, /* getItemViewType(pos),*/pos)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    fun add(data: T) {
        dataList.add(data)
        notifyItemInserted(dataList.size)
    }

    fun add(pos: Int, data: T) {
        dataList.add(pos, data)
        notifyItemInserted(pos)
    }

    fun remove(data: T) {
        dataList.remove(data)
        notifyItemRemoved(dataList.indexOf(data))
    }

    fun remove(pos: Int) {
        dataList.removeAt(pos)
        notifyItemRemoved(pos)
    }

    fun clear() {
        dataList.clear()
        notifyDataSetChanged()
    }

    fun onItemClick(f: (rootView: View, data: T, pos: Int) -> Unit): RecyclerAdapter<T> {
        onItemClickListener = f
        return this
    }

    fun onItemLongClick(f: (rootView: View, data: T, pos: Int) -> Boolean): RecyclerAdapter<T> {
        onItemLongClickListener = f
        return this
    }

    protected abstract fun bindData(holder: ViewHolder<T>, data: T, /*int type,*/ pos: Int)


}
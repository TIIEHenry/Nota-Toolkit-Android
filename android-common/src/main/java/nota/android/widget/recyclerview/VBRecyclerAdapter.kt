package nota.android.widget.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType

abstract class VBRecyclerAdapter<D, VB : ViewBinding> :
    RecyclerView.Adapter<ViewBindingHolder<VB>>() {
    val data = mutableListOf<D>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingHolder<VB> {
        return ViewBindingHolder(createViewBinding(LayoutInflater.from(parent.context)))
    }

    /**
     * 查找 Any 类上泛型为[cls]类型的class，如果不存在则返回null
     * @param cls 要查找的泛型的类型
     */
    @Suppress("UNCHECKED_CAST")
    internal fun <T> Any.findActualGenericsClass(cls: Class<*>): Class<T>? {
        val genericSuperclass = javaClass.genericSuperclass
        if (genericSuperclass !is ParameterizedType) {
            return null
        }
        // 获取类的所有泛型参数数组
        val actualTypeArguments = genericSuperclass.actualTypeArguments
        // 遍历泛型数组
        actualTypeArguments.forEach {
            if (it is Class<*> && cls.isAssignableFrom(it)) {
                return it as Class<T>
            } else if (it is ParameterizedType) {
                val rawType = it.rawType
                if (rawType is Class<*> && cls.isAssignableFrom(rawType)) {
                    return rawType as Class<T>
                }
            }
        }
        return null
    }

    /**
     * Create ViewBinding
     */
    @Suppress("UNCHECKED_CAST")
    fun createViewBinding(layoutInflater: LayoutInflater): VB {
        val actualGenericsClass = findActualGenericsClass<VB>(ViewBinding::class.java)
            ?: throw NullPointerException("Can not find a ViewBinding Generics in ${javaClass.simpleName}")
        try {
            val inflate =
                actualGenericsClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
            return inflate.invoke(null, layoutInflater) as VB
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        throw NullPointerException("Can not find a ViewBinding Generics in ${javaClass.simpleName}")
    }

    fun getItem(position: Int): D {
        return data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refresh(data: List<D>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun remove(item: D) {
        val position = data.indexOf(item)
        if (position != -1) {
            data.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun remove(items: List<D>) {
        data.removeAll(items)
        notifyDataSetChanged()
    }

    fun add(items: List<D>) {
        data.addAll(items)
        notifyItemRangeInserted(data.size - items.size, items.size)
    }

    fun add(item: D) {
        data.add(item)
        notifyItemInserted(data.size - 1)
    }

}

package tiiehenry.android.view.base.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import tiiehenry.android.view.base.holder.IViewHolder;
import tiiehenry.android.view.base.holder.OnItemClickListener;
import tiiehenry.android.view.base.holder.OnItemLongClickListener;

public interface IAdapter<IADAPTER, DATATYPE, VH extends IViewHolder> {


    /**
     * 数据源
     *
     * @return
     */
    List<DATATYPE> getDataList();

    @NonNull
    INotifier getNotifier();

    @NonNull
    IADAPTER getInstance();

    @NonNull
    IADAPTER setOnItemClickListener(@Nullable OnItemClickListener<DATATYPE> listener);

    @NonNull
    IADAPTER setOnItemLongClickListener(@Nullable OnItemLongClickListener<DATATYPE> listener);

    /**
     * 获取列表项
     *
     * @param position
     * @return
     */
    default DATATYPE getItem(int position) {
        return getDataList().get(position);
    }

    default int getItemCount() {
        return getDataList().size();
    }

    default boolean isEmpty() {
        return getItemCount() == 0;
    }

    /**
     * 插入元素到指定位置
     *
     * @param pos
     * @param item
     * @return
     */
    default IADAPTER insert(int pos, @NonNull DATATYPE item) {
        getDataList().add(pos, item);
        getNotifier().notifyItemInserted(pos);
        return getInstance();
    }

    /**
     * 插入元素到指定位置
     *
     * @param pos
     * @param collection
     * @return
     */
    default IADAPTER insert(int pos, @NonNull Collection<DATATYPE> collection) {
        getDataList().addAll(pos, collection);
        getNotifier().notifyItemRangeInserted(pos, collection.size());
        return getInstance();
    }

    /**
     * 插入元素到指定位置
     *
     * @param pos
     * @param array
     * @return
     */
    default IADAPTER insert(int pos, @NonNull DATATYPE[] array) {
        return insert(pos, Arrays.asList(array));
    }

    /**
     * 在列表末端增加一项
     *
     * @param item
     * @return
     */
    default IADAPTER add(@NonNull DATATYPE item) {
        getDataList().add(item);
        getNotifier().notifyItemInserted(getDataList().size() - 1);
        return getInstance();
    }

    /**
     * 添加到末尾
     *
     * @param collection
     * @return
     */
    default IADAPTER add(@NonNull Collection<DATATYPE> collection) {
        if (!collection.isEmpty()) {
            getDataList().addAll(collection);
            getNotifier().notifyDataSetChanged();
        }
        return getInstance();
    }

    /**
     * 添加到末尾
     *
     * @param array
     * @return
     */
    default IADAPTER add(@NonNull DATATYPE[] array) {
        return add(Arrays.asList(array));
    }

    /**
     * 删除列表中指定索引的数据
     *
     * @param pos
     * @return
     */
    default IADAPTER remove(int pos) {
        getDataList().remove(pos);
        getNotifier().notifyItemRemoved(pos);
        return getInstance();
    }

    /**
     * 删除列表中的数据
     *
     * @param item
     * @return
     */
    default IADAPTER remove(DATATYPE item) {
        int index=getDataList().indexOf(item);
        if (index!=-1){
            return remove(index);
        }
        return getInstance();
    }

    /**
     * 删除列表中指定索引的数据
     *
     * @param collection data collection
     * @return
     */
    default IADAPTER remove(@NonNull Collection<DATATYPE> collection) {
        getDataList().removeAll(collection);
        getNotifier().notifyDataSetChanged();
        return getInstance();
    }

    /**
     * 删除列表中指定索引的数据
     *
     * @param array data array
     * @return
     */
    default IADAPTER remove(@NonNull DATATYPE[] array) {
        return remove(Arrays.asList(array));
    }

    /**
     * 刷新列表中指定位置的数据
     *
     * @param pos
     * @param item
     * @return
     */
    default IADAPTER refresh(int pos, @NonNull DATATYPE item) {
        getDataList().set(pos, item);
        getNotifier().notifyItemChanged(pos);
        return getInstance();
    }

    /**
     * 刷新列表数据
     *
     * @param collection
     * @return
     */
    default IADAPTER refresh(@NonNull Collection<DATATYPE> collection) {
        getDataList().clear();
        getDataList().addAll(collection);
        getNotifier().notifyDataSetChanged();
        return getInstance();
    }

    /**
     * 刷新列表数据
     *
     * @param array
     * @return
     */
    default IADAPTER refresh(@NonNull DATATYPE[] array) {
        return refresh(Arrays.asList(array));
    }

}

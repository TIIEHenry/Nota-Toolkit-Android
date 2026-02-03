package tiiehenry.android.view.spinner.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import tiiehenry.android.view.base.adapter.wrapped.IAllChangedNotifier;
import tiiehenry.android.view.spinner.holder.SpinnerViewHolder;

/**
 * @author TIIEHenry
 */
public abstract class AbstractSpinnerAdapter<DATATYPE, VB extends ViewBinding> extends BaseAdapter implements IAllChangedNotifier {
    private final List<DATATYPE> mData = new ArrayList<>();

    public AbstractSpinnerAdapter() {
        super();
    }

    public AbstractSpinnerAdapter(@NonNull Collection<DATATYPE> list) {
        this();
        mData.addAll(list);
    }

    public AbstractSpinnerAdapter(@NonNull DATATYPE[] data) {
        this();
        if (data.length > 0) {
            mData.addAll(Arrays.asList(data));
        }
    }

    @NonNull
    public abstract SpinnerViewHolder<VB> onCreateViewHolder(@NonNull ViewGroup parent, int position);

    @NonNull
    public abstract SpinnerViewHolder<VB> onCreateDropDownViewHolder(@NonNull ViewGroup parent, int position);

    public abstract void bindData(@NonNull SpinnerViewHolder<VB> holder, @NonNull DATATYPE item, int position);

    public abstract void bindDropDownData(@NonNull SpinnerViewHolder<VB> holder, @NonNull DATATYPE item, int position);

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpinnerViewHolder<VB> holder = onCreateViewHolder(parent, position);
        DATATYPE data = getData(position);
//        holder.setData(data);
        bindData(holder, data, position);
        holder.itemView.setTag(holder);
        return holder.itemView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        SpinnerViewHolder<VB> holder = onCreateDropDownViewHolder(parent, position);
        DATATYPE data = getData(position);
//        holder.setData(data);
        bindDropDownData(holder, data, position);
        holder.itemView.setTag(holder);
        return holder.itemView;
    }

    public List<DATATYPE> getDataList() {
        return mData;
    }

    @Override
    public Object getItem(int position) {
        return getData(position);
    }

    public DATATYPE getData(int position) {
        return mData.get(position);
    }


    /**
     * 获取元素位置
     *
     * @param item
     * @return 不存在：-1
     */
    int getPosition(@NonNull DATATYPE item) {
        return mData.indexOf(item);
    }


    /**
     * 清空数据
     *
     * @return
     */
    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    /**
     * 刷新列表中指定位置的数据
     *
     * @param pos
     * @param item
     * @return
     */
    public void refresh(int pos, @NonNull DATATYPE item) {
        mData.set(pos, item);
        notifyItemChanged(pos);
    }

    /**
     * 刷新列表数据
     *
     * @param collection
     * @return
     */
    public void refresh(@NonNull Collection<DATATYPE> collection) {
        mData.clear();
        mData.addAll(collection);
        notifyDataSetChanged();
    }

    /**
     * 刷新列表数据
     *
     * @param array
     * @return
     */
    public void refresh(@NonNull DATATYPE[] array) {
        refresh(Arrays.asList(array));
    }

}


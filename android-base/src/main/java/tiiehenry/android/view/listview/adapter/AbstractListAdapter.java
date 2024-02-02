package tiiehenry.android.view.listview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import tiiehenry.android.view.base.adapter.INotifier;
import tiiehenry.android.view.base.holder.OnItemClickListener;
import tiiehenry.android.view.base.holder.OnItemLongClickListener;
import tiiehenry.android.view.listview.holder.IListViewHolder;
import tiiehenry.android.view.listview.holder.ListViewHolder;

public abstract class AbstractListAdapter<IADAPTER extends AbstractListAdapter
        , DATATYPE>
        extends BaseAdapter
        implements IListAdapter<IADAPTER, DATATYPE, IListViewHolder>, INotifier {

    /**
     * 数据源
     */
    protected final List<DATATYPE> mData = new ArrayList<>();

    /**
     * 点击监听
     */
    private OnItemClickListener<DATATYPE> mClickListener;
    /**
     * 长按监听
     */
    private OnItemLongClickListener<DATATYPE> mLongClickListener;

    public AbstractListAdapter() {

    }

    public AbstractListAdapter(@NonNull Collection<DATATYPE> list) {
        mData.addAll(list);
    }

    public AbstractListAdapter(@NonNull DATATYPE[] data) {
        if (data.length > 0) {
            mData.addAll(Arrays.asList(data));
        }
    }

    /**
     * 构建自定义的ViewHolder
     *
     * @param parent
     * @return
     */
    @NonNull
    protected abstract ListViewHolder getViewHolder(@NonNull ViewGroup parent);

    /**
     * 绑定数据
     *
     * @param holder
     * @param position 索引
     * @param item     列表项
     */
    protected abstract void bindData(@NonNull ListViewHolder holder, int position, @NonNull DATATYPE item);

    /**
     * 加载布局获取控件
     *
     * @param parent   父布局
     * @param layoutId 布局ID
     * @return
     */
    protected View inflateView(ViewGroup parent, @LayoutRes int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewHolder hv;
        if (null == convertView) {
            hv = onCreateViewHolder(parent, position);
            convertView = hv.getItemView();
            convertView.setTag(hv);
        } else {
            hv = (ListViewHolder) convertView.getTag();
        }
        hv.setLayoutPosition(position);
        onBindViewHolder(hv, position);
        return convertView;
    }


    @NonNull
    protected ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        final ListViewHolder holder = getViewHolder(parent);
        if (mClickListener != null) {
            holder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(holder.getItemView(), getData(holder.getLayoutPosition()), holder.getLayoutPosition());
                }
            });
        }
        if (mLongClickListener != null) {
            holder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mLongClickListener.onItemLongClick(holder.getItemView(), getData(holder.getLayoutPosition()), holder.getLayoutPosition());
                    return true;
                }
            });
        }
        return holder;
    }

    protected void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        bindData(holder, position, mData.get(position));
    }


    /**
     * 设置列表项点击监听
     *
     * @param listener
     * @return
     */
    @NonNull
    public IADAPTER setOnItemClickListener(OnItemClickListener<DATATYPE> listener) {
        mClickListener = listener;
        return getInstance();
    }

    /**
     * 设置列表项长按监听
     *
     * @param listener
     * @return
     */
    @NonNull
    public IADAPTER setOnItemLongClickListener(OnItemLongClickListener<DATATYPE> listener) {
        mLongClickListener = listener;
        return getInstance();
    }

    @Override
    public int getCount() {
        return getDataCount();
    }

    @Override
    public DATATYPE getItem(int position) {
        return getData(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getDataCount() {
        return getDataList().size();
    }

    @Override
    public List<DATATYPE> getDataList() {
        return mData;
    }

    public int getItemCount() {
        return getDataCount();
    }

    @NonNull
    @Override
    public INotifier getNotifier() {
        return this;
    }
}


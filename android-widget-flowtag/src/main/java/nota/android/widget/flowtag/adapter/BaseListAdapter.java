package nota.android.widget.flowtag.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.WeakHashMap;

/**
 * 简单的集合列表适配器
 *
 * @param <T>
 * @param <H>
 * @author XUE
 */
public abstract class BaseListAdapter<T, H> extends XListAdapter<T> {

    public BaseListAdapter(Context context) {
        super(context);
    }

    public BaseListAdapter(Context context, List<T> data) {
        super(context, data);
    }

    public BaseListAdapter(Context context, T[] data) {
        super(context, data);
    }

//    private WeakHashMap<View,H> viewHWeakHashMap=new WeakHashMap<>();
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        H holder;
        if (convertView == null) {
            convertView = View.inflate(getContext(), getLayoutId(), null);
            holder = newViewHolder(convertView);
//            viewHWeakHashMap.put(convertView,holder);
            convertView.setTag(holder);
        } else {
//            holder =  viewHWeakHashMap.get(convertView);
            holder = (H) convertView.getTag();
        }

        T item = getItem(position);
        if (item != null) {
            convert(holder, item, position);
        }
        return convertView;
    }

    /**
     * 创建ViewHolder
     *
     * @param convertView
     * @return
     */
    protected abstract H newViewHolder(View convertView);

    /**
     * 获取适配的布局ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 转换布局
     *
     * @param holder
     * @param item
     * @param position
     */
    protected abstract void convert(H holder, T item, int position);
}

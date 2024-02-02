package tiiehenry.android.view.listview.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.Collection;

import tiiehenry.android.view.listview.holder.ListViewHolder;


public abstract class InflateListAdapter<T> extends AbstractListAdapter<InflateListAdapter<T>, T> {

    public InflateListAdapter() {
        super();
    }

    public InflateListAdapter(Collection<T> list) {
        super(list);
    }

    public InflateListAdapter(T[] data) {
        super(data);
    }

    /**
     * 适配的布局
     *
     * @param parent
     * @return layout
     */
    protected abstract View inflateItemLayout(ViewGroup parent);

    @NonNull
    @Override
    protected ListViewHolder getViewHolder(@NonNull ViewGroup parent) {
        return new ListViewHolder(inflateItemLayout(parent));
    }


    @NonNull
    @Override
    public InflateListAdapter<T> getInstance() {
        return this;
    }
}
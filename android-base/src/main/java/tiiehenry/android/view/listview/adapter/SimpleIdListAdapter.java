package tiiehenry.android.view.listview.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import java.util.Collection;


public abstract class SimpleIdListAdapter<T> extends InflateListAdapter<T> {

    private final int layoutId;

    public SimpleIdListAdapter(@LayoutRes int layoutId) {
        super();
        this.layoutId = layoutId;
    }

    public SimpleIdListAdapter(@LayoutRes int layoutId, @NonNull Collection<T> list) {
        super(list);
        this.layoutId = layoutId;
    }

    public SimpleIdListAdapter(@LayoutRes int layoutId, @NonNull T[] data) {
        super(data);
        this.layoutId = layoutId;
    }

    /**
     * 适配的布局
     *
     * @param parent
     * @return layout
     */
    @Override
    protected View inflateItemLayout(ViewGroup parent) {
        return inflateView(parent, layoutId);
    }

    @NonNull
    @Override
    public InflateListAdapter<T> getInstance() {
        return this;
    }

}

package tiiehenry.android.view.listview.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.Collection;


/**
 * @author TIIEHenry
 */
public abstract class BaseIdListAdapter<T>
        extends InflateListAdapter<T> {

    public BaseIdListAdapter() {
        super();
    }

    public BaseIdListAdapter(@NonNull Collection<T> list) {
        super(list);
    }

    public BaseIdListAdapter(@NonNull T[] data) {
        super(data);
    }

    /**
     * 适配的布局
     *
     * @return
     */
    protected abstract int getItemLayoutId();

    @Override
    protected View inflateItemLayout(ViewGroup parent) {
        return inflateView(parent, getItemLayoutId());
    }

    @NonNull
    @Override
    public InflateListAdapter<T> getInstance() {
        return this;
    }
}


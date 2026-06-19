package nota.android.base.view.listview.adapter;

import androidx.annotation.NonNull;

import nota.android.base.view.base.adapter.IAdapter;
import nota.android.base.view.base.adapter.INotifier;
import nota.android.base.view.base.adapter.wrapped.IAllChangedNotifier;
import nota.android.base.view.listview.holder.IListViewHolder;

public interface IListAdapter<IADAPTER extends IListAdapter
        , DATATYPE
        , VH extends IListViewHolder>
        extends IAdapter<IADAPTER, DATATYPE>, IAllChangedNotifier {


    @NonNull
    @Override
    default INotifier getNotifier() {
        return getInstance();
    }
}

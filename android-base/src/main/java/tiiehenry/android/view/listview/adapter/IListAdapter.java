package tiiehenry.android.view.listview.adapter;

import androidx.annotation.NonNull;

import tiiehenry.android.view.base.adapter.IAdapter;
import tiiehenry.android.view.base.adapter.INotifier;
import tiiehenry.android.view.base.adapter.wrapped.IAllChangedNotifier;
import tiiehenry.android.view.listview.holder.IListViewHolder;

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

package nota.android.base.view.spinner.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import nota.android.base.view.base.adapter.IAdapter;
import nota.android.base.view.base.adapter.INotifier;
import nota.android.base.view.base.adapter.wrapped.IAllChangedNotifier;
import nota.android.base.view.base.holder.OnItemClickListener;
import nota.android.base.view.base.holder.OnItemLongClickListener;
import nota.android.base.view.spinner.holder.ISpinnerViewHolder;

public interface ISpinnerAdapter<IADAPTER extends ISpinnerAdapter
        , DATATYPE
        , ISpinnerViewHolder>
        extends IAdapter<IADAPTER, DATATYPE>, IAllChangedNotifier {


    @NonNull
    @Override
    default INotifier getNotifier() {
        return getInstance();
    }
}

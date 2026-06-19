package nota.android.fragment.adapter;

import androidx.annotation.NonNull;

import nota.android.base.view.base.adapter.IAdapter;
import nota.android.base.view.base.adapter.INotifier;
import nota.android.base.view.base.adapter.wrapped.IAllChangedNotifier;

public interface IFragmentAdapter<IADAPTER extends IFragmentAdapter
        , DATATYPE>
        extends IAdapter<IADAPTER, DATATYPE>, IAllChangedNotifier {

    @NonNull
    @Override
    default INotifier getNotifier() {
        return getInstance();
    }
}

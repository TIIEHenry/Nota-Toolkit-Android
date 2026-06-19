package nota.android.base.view.base.adapter;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public interface IAdapter<IADAPTER, DATATYPE> {


    /**
     * 数据源
     *
     * @return
     */
    List<DATATYPE> getDataList();

    default int getDataCount() {
        return getDataList().size();
    }

    default DATATYPE getData(int position) {
        return getDataList().get(position);
    }

    @NonNull
    INotifier getNotifier();

    @NonNull
    IADAPTER getInstance();

}

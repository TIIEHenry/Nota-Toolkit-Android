package tiiehenry.android.view.base.adapter;

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

    @NonNull
    INotifier getNotifier();

    @NonNull
    IADAPTER getInstance();

}

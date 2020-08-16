package tiiehenry.android.view.recyclerview.adapter;

import androidx.annotation.NonNull;

import tiiehenry.android.view.base.adapter.IAdapter;
import tiiehenry.android.view.base.holder.IViewHolder;
import tiiehenry.android.view.recyclerview.holder.IRecyclerViewHolder;

public interface IRecyclerAdapter<IADAPTER extends IRecyclerAdapter, DATATYPE, VH extends IRecyclerViewHolder> extends IAdapter<IADAPTER,DATATYPE,VH> {

}

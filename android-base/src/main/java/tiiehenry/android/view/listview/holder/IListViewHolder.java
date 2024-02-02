package tiiehenry.android.view.listview.holder;

import tiiehenry.android.view.base.holder.IViewHolder;

public interface IListViewHolder<IVIEWHOLDER> extends IViewHolder<IVIEWHOLDER> {

    void setLayoutPosition(int position);

    int getLayoutPosition();
}
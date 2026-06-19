package nota.android.base.view.listview.holder;

import nota.android.base.view.base.holder.IViewHolder;

public interface IListViewHolder<IVIEWHOLDER> extends IViewHolder<IVIEWHOLDER> {

    void setLayoutPosition(int position);

    int getLayoutPosition();
}
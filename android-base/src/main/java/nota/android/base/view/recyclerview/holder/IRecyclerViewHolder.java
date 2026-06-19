package nota.android.base.view.recyclerview.holder;

import nota.android.base.view.base.holder.IViewHolder;

public interface IRecyclerViewHolder<IVIEWHOLDER> extends IViewHolder<IVIEWHOLDER> {
    int getLayoutPosition();

    int getPosition();
}

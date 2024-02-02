package tiiehenry.android.view.listview.holder;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;


public class ListViewHolder implements IListViewHolder<ListViewHolder> {
    @NonNull
    private final View itemView;

    private final SparseArray<View> mViews;
    private int layoutPosition;

    public ListViewHolder(View itemView) {
        this.itemView = itemView;
        mViews = new SparseArray<>();
    }

    @Override
    public void setLayoutPosition(int position) {
        layoutPosition=position;
    }

    @Override
    public int getLayoutPosition() {
        return layoutPosition;
    }

    @NonNull
    @Override
    public View getItemView() {
        return itemView;
    }

    @Override
    public <T extends View> T findView(int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mViews.put(id, view);
        }
        return (T) view;
    }

    @Override
    public void clearViewCache() {
        if (mViews != null) {
            mViews.clear();
        }
    }

    @NonNull
    @Override
    public ListViewHolder getInstance() {
        return this;
    }
}

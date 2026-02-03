package nota.android.widget.treeview;

import androidx.annotation.NonNull;

public interface TreeNodeListener<T extends LayoutItemType, VH extends TreeViewViewHolder> {
    /**
     * @return weather consume the click event.
     */
    boolean onClick(@NonNull TreeNode<T> node, @NonNull VH holder, @NonNull TreeViewAdapter<T, VH> adapter, int position);

    boolean onLongClick(@NonNull TreeNode<T> node, @NonNull VH holder, @NonNull TreeViewAdapter<T, VH> adapter, int position);

}

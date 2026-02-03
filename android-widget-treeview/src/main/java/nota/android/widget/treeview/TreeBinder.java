package nota.android.widget.treeview;

import android.view.View;

import androidx.annotation.NonNull;


public abstract class TreeBinder<T extends LayoutItemType, VH extends TreeViewViewHolder> implements LayoutItemType {
   private final TreeNodeListener<T, VH> listener;

   public TreeBinder(TreeNodeListener<T, VH> listener) {
      this.listener = listener;
   }

   public abstract VH provideViewHolder(View itemView);

   public abstract void bindView(@NonNull VH holder, int position, @NonNull TreeNode<T> node);

   public TreeNodeListener<T, VH> getListener() {
      return listener;
   }

}
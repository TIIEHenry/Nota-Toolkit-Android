package tiiehenry.android.view.treeview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreeViewAdapter<T extends LayoutItemType, VH extends TreeViewViewHolder> extends RecyclerView.Adapter<TreeViewViewHolder> {
    private static final String KEY_IS_EXPAND = "IS_EXPAND";
    private final List<TreeBinder<T, VH>> viewBinders;
    private final List<TreeNode<T>> displayNodes;
    private int padding = 30;
    private boolean toCollapseChild;
    private final List<TreeNode<T>> rootNodeList = new ArrayList<>();

    //    public TreeAdapter(TreeBinder<T, VH> viewBinder) {
//        this(null, new ArrayList<>());
//        viewBinders.add(viewBinder);
//    }
    public TreeViewAdapter(List<TreeBinder<T, VH>> viewBinders) {
        this(null, viewBinders);
    }

    public TreeViewAdapter(List<TreeNode<T>> nodes, List<TreeBinder<T, VH>> viewBinders) {
        displayNodes = new ArrayList<>();
        if (nodes != null) {
            addToDisplayNodes(nodes);
        }
        this.viewBinders = viewBinders;
    }


    /**
     * 从nodes的结点中寻找展开了的非叶结点，添加到displayNodes中。
     *
     * @param nodes 基准点
     */
    private void addToDisplayNodes(List<TreeNode<T>> nodes) {
        for (TreeNode<T> node : nodes) {
            displayNodes.add(node);
            if (node.isBranch() && node.isExpand()) {
                addToDisplayNodes(node.getChildList());
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return displayNodes.get(position).getContent().getLayoutId();
    }

    public TreeBinder<T, VH> getBinder(int viewType) {
        if (viewBinders.size() == 1) {
            return viewBinders.get(0);
        }
        for (TreeBinder<T, VH> viewBinder : viewBinders) {
            if (viewBinder.getLayoutId() == viewType) {
                return viewBinder;
            }
        }
        return viewBinders.get(0);
    }

    private TreeBinder<T, VH> getBinder(TreeNode<T> node) {
        return getBinder(node.getContent().getLayoutId());
    }

    public TreeBinder<T, VH> getBinderForIndex(int position) {
        return getBinder(getItemViewType(position));
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return getBinder(viewType).provideViewHolder(v);
    }

    public int findPosition(T content) {
        for (int i = 0; i < displayNodes.size(); i++) {
            TreeNode<T> displayNode = displayNodes.get(i);
            if (displayNode.getContent() == content) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onBindViewHolder(@NonNull TreeViewViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (!payloads.isEmpty()) {
            Bundle b = (Bundle) payloads.get(0);
            for (String key : b.keySet()) {
                switch (key) {
                    case KEY_IS_EXPAND://notify diff
                        boolean isExpanded = b.getBoolean(key);
//                        Log.e("TreeViewAdapter", "onBindViewHolder: " + isExpanded);
                        TreeNode<T> node = displayNodes.get(position);
//                        if (isExpanded && node.isExpand()) {
//                            node.toggle();
//                        }
                        if (!isExpanded)
                            node.expand();
                        TreeNodeListener<T, VH> listener = getBinder(node).getListener();
//                        Log.e("tree", "onBindViewHolder: " );
                        if (listener != null) {
                            listener.onClick(node, (VH) holder, this, position);
                        }
                        if (!isExpanded)
                            node.collapse();
                        break;
                }
            }
        }
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(@NonNull TreeViewViewHolder holder, int position) {
        holder.itemView.setPadding(displayNodes.get(position).getHeight() * padding, 3, 3, 3);
        holder.itemView.setOnClickListener(v -> {
            TreeNode<T> selectedNode = displayNodes.get(holder.getLayoutPosition());
            TreeNodeListener<T, VH> listener = getBinder(selectedNode).getListener();
            // Prevent multi-click during the short interval.
            if (System.currentTimeMillis() - holder.time < 500) {
                return;
            }
            holder.time = System.currentTimeMillis();

            if (listener != null && listener.onClick(selectedNode, (VH) holder, this, position)) {
                return;
            }
            if (selectedNode.isLeaf()) {
                return;
            }
            boolean isExpand = selectedNode.isExpand();
            int positionStart = displayNodes.indexOf(selectedNode) + 1;
            if (!isExpand) {
                notifyItemRangeInserted(positionStart, addChildNodes(selectedNode, positionStart));
            } else {
                notifyItemRangeRemoved(positionStart, removeChildNodes(selectedNode, true));
            }
        });
        holder.itemView.setOnLongClickListener(v -> {
            TreeNode<T> selectedNode = displayNodes.get(holder.getLayoutPosition());
            TreeNodeListener<T, VH> listener = getBinder(selectedNode).getListener();
            if (listener != null) {
                return listener.onLongClick(selectedNode, (VH) holder, this, position);
            }
            return false;
        });
        for (TreeBinder<T, VH> viewBinder : viewBinders) {
            if (viewBinder.getLayoutId() == displayNodes.get(position).getContent().getLayoutId()) {
                viewBinder.bindView((VH) holder, position, displayNodes.get(position));
            }
        }
    }

    private int addChildNodes(TreeNode<T> pNode, int startIndex) {
        List<TreeNode<T>> childList = pNode.getChildList();
        int addChildCount = 0;
        for (TreeNode<T> treeNode : childList) {
            displayNodes.add(startIndex + addChildCount++, treeNode);
            if (treeNode.isExpand()) {
                addChildCount += addChildNodes(treeNode, startIndex + addChildCount);
            }
        }
        if (!pNode.isExpand()) {
            pNode.toggle();
        }
        return addChildCount;
    }


    /**
     * 移除 node 的孩子
     *
     * @param pNode
     * @return
     */
    private int removeChildNodes(TreeNode<T> pNode) {
        return removeChildNodes(pNode, true);
    }

    private int removeChildNodes(TreeNode<T> pNode, boolean shouldToggle) {
        if (pNode.isLeaf()) {
            return 0;
        }
        List<TreeNode<T>> childList = pNode.getChildList();
        int removeChildCount = childList.size();
        displayNodes.removeAll(childList);
        for (TreeNode<T> child : childList) {
            if (child.isExpand()) {
                if (toCollapseChild) {
                    child.toggle();
                }
                removeChildCount += removeChildNodes(child, false);
            }
        }
        if (shouldToggle) {
            pNode.toggle();
        }
        return removeChildCount;
    }

    @Override
    public int getItemCount() {
        return displayNodes == null ? 0 : displayNodes.size();
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public void ifCollapseChildWhileCollapseParent(boolean toCollapseChild) {
        this.toCollapseChild = toCollapseChild;
    }


    public void refresh(List<TreeNode<T>> treeNodes) {
        rootNodeList.clear();
        rootNodeList.addAll(treeNodes);
//        rootNodeList = treeNodes;
        displayNodes.clear();
        addToDisplayNodes(treeNodes);
        notifyDataSetChanged();
    }

    public void refresh() {
        displayNodes.clear();
        addToDisplayNodes(rootNodeList);
        notifyDataSetChanged();
    }

    public boolean isInRootNodesList(TreeNode<T> node) {
        return rootNodeList.contains(node);
    }

    public List<TreeNode<T>> getRootNodesList() {
        return rootNodeList;
    }

    public Iterator<TreeNode<T>> getDisplayNodesIterator() {
        return displayNodes.iterator();
    }

    private void notifyDiff(final List<TreeNode<T>> temp) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return temp.size();
            }

            @Override
            public int getNewListSize() {
                return displayNodes.size();
            }

            // 判断是否是同一个 item
            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return TreeViewAdapter.this.areItemsTheSame(temp.get(oldItemPosition), displayNodes.get(newItemPosition));
            }

            // 如果是同一个 item 判断内容是否相同
            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return TreeViewAdapter.this.areContentsTheSame(temp.get(oldItemPosition), displayNodes.get(newItemPosition));
            }

            @Nullable
            @Override
            public Object getChangePayload(int oldItemPosition, int newItemPosition) {
                return TreeViewAdapter.this.getChangePayload(temp.get(oldItemPosition), displayNodes.get(newItemPosition));
            }
        });
        diffResult.dispatchUpdatesTo(this);
    }

    private Object getChangePayload(TreeNode<T> oldNode, TreeNode<T> newNode) {
        Bundle diffBundle = new Bundle();
        if (newNode.isExpand() != oldNode.isExpand()) {
            diffBundle.putBoolean(KEY_IS_EXPAND, newNode.isExpand());
        }
        if (diffBundle.size() == 0) {
            return null;
        }
        return diffBundle;
    }

    private boolean areContentsTheSame(TreeNode<T> oldNode, TreeNode<T> newNode) {
        return oldNode.getContent() != null && oldNode.getContent().equals(newNode.getContent())
                && oldNode.isExpand() == newNode.isExpand();
    }

    private boolean areItemsTheSame(TreeNode<T> oldNode, TreeNode<T> newNode) {
        return oldNode.getContent() != null && oldNode.getContent().equals(newNode.getContent());
    }

    /**
     * expand all root nodes.
     */
    public void expandAll() {
        for (TreeNode<T> node : rootNodeList) {
            node.expand();
        }
        refresh();
    }

    private List<TreeNode<T>> findRootListFromDisplayNode() {
        List<TreeNode<T>> roots = new ArrayList<>();
        for (TreeNode<T> displayNode : displayNodes) {
            if (displayNode.isRoot()) {
                roots.add(displayNode);
            }
        }
        return roots;
    }

    private List<TreeNode<T>> findRootChildListFromDisplayNode() {
        List<TreeNode<T>> roots = new ArrayList<>();
        for (TreeNode<T> displayNode : displayNodes) {
            if (displayNode.isRoot() && displayNode.isExpand()) {
                roots.addAll(displayNode.getChildList());
            }
        }
        return roots;
    }

    /**
     * collapse all root nodes.
     */
    public void collapseAll() {
        /*for (TreeNode<T> node : rootNodeList) {
            node.collapse();
        }
        refresh();*/
//        // Back up the nodes are displaying.
        List<TreeNode<T>> temp = backupDisplayNodes();
//        //find all root nodes.
        List<TreeNode<T>> roots = findRootListFromDisplayNode();

//        //Close all root nodes.
        for (TreeNode<T> root : roots) {
            if (root.isExpand()) {
                removeChildNodes(root);
            }
        }
        notifyDiff(temp);
    }

    /**
     * collapse all root nodes.
     */
    public void collapseAllExceptRoots() {
        // Back up the nodes are displaying.
        List<TreeNode<T>> temp = backupDisplayNodes();
        //find root 1 level child nodes.
        List<TreeNode<T>> rootChildNodes = findRootChildListFromDisplayNode();

        //Close all root nodes.
        for (TreeNode<T> rootChildNode : rootChildNodes) {
            if (rootChildNode.isExpand()) {
                removeChildNodes(rootChildNode);
            }
        }
        notifyDiff(temp);
//        for (TreeNode<T> node : rootChildNodes) {
//            node.collapse();
//        }
//        refresh();
    }

    @NonNull
    private List<TreeNode<T>> backupDisplayNodes() {
        List<TreeNode<T>> temp = new ArrayList<>();
        for (TreeNode<T> displayNode : displayNodes) {
            try {
                temp.add(displayNode.clone());
            } catch (CloneNotSupportedException e) {
                temp.add(displayNode);
            }
        }
        return temp;
    }

    public void collapseNode(TreeNode<T> pNode) {
        List<TreeNode<T>> temp = backupDisplayNodes();
        removeChildNodes(pNode);
        notifyDiff(temp);
    }

    public void collapseBrotherNode(TreeNode<T> pNode) {
        List<TreeNode<T>> temp = backupDisplayNodes();
        if (pNode.isRoot()) {
            List<TreeNode<T>> roots = new ArrayList<>();
            for (TreeNode<T> displayNode : displayNodes) {
                if (displayNode.isRoot()) {
                    roots.add(displayNode);
                }
            }
            //Close all root nodes.
            for (TreeNode<T> root : roots) {
                if (root.isExpand() && !root.equals(pNode)) {
                    removeChildNodes(root);
                }
            }
        } else {
            TreeNode<T> parent = pNode.getParent();
            if (parent == null) {
                return;
            }
            List<TreeNode<T>> childList = parent.getChildList();
            for (TreeNode<T> node : childList) {
                if (node.equals(pNode) || !node.isExpand()) {
                    continue;
                }
                removeChildNodes(node);
            }
        }
        notifyDiff(temp);
    }

}
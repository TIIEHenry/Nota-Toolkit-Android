package nota.android.widget.treeview;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T extends LayoutItemType> implements Cloneable {
    private static final int UNDEFINE = -1;
    private T content;
    private TreeNode<T> parent;
    private final ArrayList<TreeNode<T>> childList = new ArrayList<>();
    private boolean isExpand;
    //the tree high
    private int height = UNDEFINE;

    public TreeNode(@NonNull T content) {
        this.content = content;
    }


    public int getHeight() {
        if (isRoot()) {
            height = 0;
        } else if (height == UNDEFINE) {
            height = parent.getHeight() + 1;
        }
        return height;
    }

    public boolean isRoot() {
        return parent == null;
    }

    /**
     * @return 是否为叶子
     */
    public boolean isLeaf() {
        return childList == null || childList.isEmpty();
    }

    /**
     * @return 是否为分支
     */
    public boolean isBranch() {
        return !isLeaf();
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public List<TreeNode<T>> getChildList() {
        return childList;
    }

    public void setChildList(List<TreeNode<T>> childList) {
        this.childList.clear();
        this.childList.addAll(childList);
        for (TreeNode<T> t : childList) {
            t.parent = this;
        }
    }

    public TreeNode<T> addChild(TreeNode<T> node) {
        childList.add(node);
        node.parent = this;
        return this;
    }

    public TreeNode<T> removeChild(TreeNode<T> node) {
        childList.remove(node);
        node.parent = null;
        return this;
    }

    public boolean toggle() {
        isExpand = !isExpand;
        return isExpand;
    }

    public void collapse() {
        if (isExpand) {
            isExpand = false;
        }
    }

    public void expand() {
        if (!isExpand) {
            isExpand = true;
        }
    }

    public boolean isExpand() {
        return isExpand;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "TreeeNode{" +
                "content=" + this.content +
                ", parent=" + (parent == null ? "null" : parent.getContent().toString()) +
                ", childList=" + childList +
                ", isExpand=" + isExpand +
                '}';
    }

    @NonNull
    @Override
    protected TreeNode<T> clone() throws CloneNotSupportedException {
        TreeNode<T> clone = new TreeNode<>(this.content);
        clone.isExpand = this.isExpand;
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return o.equals(content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}

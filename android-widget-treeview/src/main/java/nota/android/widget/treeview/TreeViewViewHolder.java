package nota.android.widget.treeview;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TreeViewViewHolder extends RecyclerView.ViewHolder {
    public long time = -1;

    public TreeViewViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * 寻找控件
     * 使用缓存
     *
     * @param id
     * @return
     */
    public <T extends View> T findView(@IdRes int id) {
        return (T) findViewById(id);
    }


    @NonNull
    public Context getContext() {
        return itemView.getContext();
    }

    /**
     * 官方调用
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T findViewById(@IdRes int viewId) {
        return itemView.findViewById(viewId);
    }

    public View getView(@IdRes int viewId) {
        return findViewById(viewId);
    }

    public ImageView getImageView(@IdRes int viewId) {
        return findView(viewId);
    }

    public ImageButton getImageButton(@IdRes int viewId) {
        return findView(viewId);
    }

    public TextView getTextView(@IdRes int viewId) {
        return findView(viewId);
    }

    public Button getButton(@IdRes int viewId) {
        return findView(viewId);
    }

    public EditText getEditText(@IdRes int viewId) {
        return findView(viewId);
    }

    public CheckBox getCheckBox(@IdRes int viewId) {
        return findView(viewId);
    }

    public Switch getSwitch(@IdRes int viewId) {
        return findView(viewId);
    }
}

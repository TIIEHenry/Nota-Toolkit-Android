package nota.android.widget.recyclerview.item;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BottomSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private final int spacing;

    public BottomSpacingItemDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int itemCount = state.getItemCount();
        int position = parent.getChildAdapterPosition(view);

        // 只有最后一个 item 才设置底部间距
        if (position == itemCount - 1) {
            outRect.bottom = spacing;
        } else {
            outRect.bottom = 0; // 其他 item 底部间距为 0
        }
    }
}
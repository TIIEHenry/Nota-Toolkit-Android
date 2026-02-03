package nota.android.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.util.AttributeSet;

//解决英文换行导致TextView 右侧留白区域过大问题
public class AdjustTextView extends androidx.appcompat.widget.AppCompatTextView {
    public AdjustTextView(Context context) {
        super(context);
    }

    public AdjustTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdjustTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = (int) Math.ceil(getMaxLineWidth(getLayout()));
        int height = getMeasuredHeight();
        setMeasuredDimension(width, height);
    }

    private float getMaxLineWidth(Layout layout) {
        Drawable leftDrawable = getCompoundDrawables()[0];
        float maximumWidth = 0.0f;
        int lines = layout.getLineCount();
        for (int i = 0; i < lines; i++) {
            float lineWidth = layout.getLineWidth(i);
            if (leftDrawable != null) {
                lineWidth = lineWidth + leftDrawable.getIntrinsicWidth() + getCompoundDrawablePadding()/* + getCompoundPaddingRight()*/;
            }
            maximumWidth = Math.max(lineWidth, maximumWidth);
        }
        return maximumWidth;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        handleLeftDrawable();
    }

    private void handleLeftDrawable() {
        Drawable leftDrawable = getCompoundDrawables()[0];
        if (leftDrawable == null) {
            return;
        }
        //获取实际行数
        int lineCount = Math.min(getLineCount(), getMaxLines());
        //获取文本高度
        int vsPace = getBottom() - getTop() - getCompoundPaddingBottom() - getCompoundPaddingTop();
        //计算位置差值
        int verticalOffset = (int) (-1 * (vsPace * (1 - 1.0f / lineCount)) / 2);
        //重新设置Bounds
        leftDrawable.setBounds(0, verticalOffset, leftDrawable.getIntrinsicWidth(),
                leftDrawable.getIntrinsicHeight() + verticalOffset);
    }

}
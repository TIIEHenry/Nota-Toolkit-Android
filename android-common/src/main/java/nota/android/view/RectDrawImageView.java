package nota.android.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class RectDrawImageView extends AppCompatImageView {

    private Paint rectPaint;
    private Rect rect;
    private float startX, startY, endX, endY;
    private boolean isDrawing = false;
    private int imageWidth, imageHeight; // 原始图片的宽高
    private int imageViewWidth, imageViewHeight; // ImageView的宽高
    private int imageLeft, imageTop; // 图片在ImageView中的偏移量

    public RectDrawImageView(Context context) {
        super(context);
        init();
    }

    public RectDrawImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RectDrawImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        rectPaint = new Paint();
        rectPaint.setColor(Color.RED); // 矩形颜色
        rectPaint.setStyle(Paint.Style.STROKE); // 描边模式
        rectPaint.setStrokeWidth(5); // 描边宽度
        rect = new Rect();
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        isDrawing = true;
                        // 默认矩形为图片边缘
                        rect.set(0, 0, getWidth(), getHeight());
                        getParent().requestDisallowInterceptTouchEvent(true);
                        invalidate();
                        return true; // 消费事件
                    case MotionEvent.ACTION_MOVE:
                        if (isDrawing) {
                            endX = event.getX();
                            endY = event.getY();
                            // 限制矩形范围在ImageView内
                            rect.set((int) Math.min(startX, endX), (int) Math.min(startY, endY),
                                    (int) Math.max(startX, endX), (int) Math.max(startY, endY));
                            invalidate(); // 重绘
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        isDrawing = false;
                        endX = event.getX();
                        endY = event.getY();
                        // 限制矩形范围在ImageView内
                        if (startX == endX) {
                            startX = 0;
                        }
                        if (startY == endY) {
                            startY = 0;
                        }
                        rect.set((int) Math.min(startX, endX), (int) Math.min(startY, endY),
                                (int) Math.max(startX, endX), (int) Math.max(startY, endY));
                        getParent().requestDisallowInterceptTouchEvent(false);
                        invalidate();
                        break;
                }
                return false; // 不消费事件，让ImageView处理其他手势
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rect, rectPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        imageViewWidth = w;
        imageViewHeight = h;
        calculateImagePosition();
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        if (drawable != null) {
            imageWidth = drawable.getIntrinsicWidth();
            imageHeight = drawable.getIntrinsicHeight();
            calculateImagePosition();
        }
    }

    // 计算图片在ImageView中的位置和偏移量
    private void calculateImagePosition() {
        if (imageWidth == 0 || imageHeight == 0 || imageViewWidth == 0 || imageViewHeight == 0) {
            return;
        }

        float scaleX = (float) imageViewWidth / imageWidth;
        float scaleY = (float) imageViewHeight / imageHeight;
        float scale = Math.min(scaleX, scaleY); // 保证图片完整显示

        int scaledImageWidth = (int) (imageWidth * scale);
        int scaledImageHeight = (int) (imageHeight * scale);

        imageLeft = (imageViewWidth - scaledImageWidth) / 2;
        imageTop = (imageViewHeight - scaledImageHeight) / 2;
    }

    /**
     * 获取矩形在原始图片中的坐标
     *
     * @return int[4] {left, top, right, bottom}
     */
    public int[] getRectOnImage() {
        int left = rect.left - imageLeft;
        int top = rect.top - imageTop;
        int right = rect.right - imageLeft;
        int bottom = rect.bottom - imageTop;

        // 将ImageView坐标转换为原始图片坐标
        float scaleX = (float) imageViewWidth / imageWidth;
        float scaleY = (float) imageViewHeight / imageHeight;
        float scale = Math.min(scaleX, scaleY);

        left = (int) (left / scale);
        top = (int) (top / scale);
        right = (int) (right / scale);
        bottom = (int) (bottom / scale);

        // 确保坐标在图片范围内
        left = Math.max(0, left);
        top = Math.max(0, top);
        right = Math.min(imageWidth, right);
        bottom = Math.min(imageHeight, bottom);

        return new int[]{left, top, right, bottom};
    }

    /**
     * 重置矩形为图片边缘
     */
    public void resetRect() {
        rect.set(0, 0, getWidth(), getHeight());
        invalidate();
    }
}
package nota.android.view;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatTextView;

public class AutoScaleTextView extends AppCompatTextView {
    private static float DEFAULT_MIN_TEXT_SIZE = 0;
    private static float DEFAULT_MAX_TEXT_SIZE = 48;
    private Paint testPaint;
    private float minTextSize;
    private float maxTextSize;
    private OnTextSizeChangedListener onTextSizeChangedListener;

    public AutoScaleTextView(Context context) {
        this(context, null);
    }

    public AutoScaleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoScaleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialise();
    }


    private void initialise() {
        testPaint = new Paint();
        testPaint.set(this.getPaint());
        maxTextSize = this.getTextSize();
        if (maxTextSize <= DEFAULT_MIN_TEXT_SIZE) {
            maxTextSize = DEFAULT_MAX_TEXT_SIZE;
        }
        minTextSize = DEFAULT_MIN_TEXT_SIZE;
    }

    private void refitText(String text, int textWidth) {
        if (textWidth > 0) {
            int availableWidth = textWidth - this.getPaddingLeft() - this.getPaddingRight();
            float trySize = maxTextSize;
            testPaint.setTextSize(trySize);
            while ((trySize > minTextSize) && (testPaint.measureText(text) > availableWidth)) {
                trySize -= 1;
                if (trySize <= minTextSize) {
                    trySize = minTextSize;
                    break;
                }
                testPaint.setTextSize(trySize);
            }
            this.setTextSize(TypedValue.COMPLEX_UNIT_PX, trySize);//TypedValue.COMPLEX_UNIT_PX不可少，将单位设置为像素
            if (onTextSizeChangedListener != null) {
                onTextSizeChangedListener.onTextSizeChanged(trySize);
            }
        }
    }

    public void setOnTextSizeChangedListener(OnTextSizeChangedListener onTextSizeChangedListener) {
        this.onTextSizeChangedListener = onTextSizeChangedListener;
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int before, int after) {
        super.onTextChanged(text, start, before, after);
        refitText(text.toString(), this.getWidth());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw) {
            refitText(this.getText().toString(), w);
        }
    }

    public interface OnTextSizeChangedListener {
        void onTextSizeChanged(float size);
    }
}


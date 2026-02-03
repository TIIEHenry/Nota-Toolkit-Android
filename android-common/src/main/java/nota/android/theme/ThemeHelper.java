package nota.android.theme;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;

public class ThemeHelper {
    // 获取attr的颜色值
    public static int getAttrColor(Context context, int attrResId) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrResId, typedValue, true);
        return typedValue.data;
    }

    public static Drawable getWindowBackground(Context context) {
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{android.R.attr.windowBackground});
        Drawable drawable = typedArray.getDrawable(0);
        typedArray.recycle();
        return drawable;
    }

    public static void setupWindowBar(Activity activity) {
        boolean light = !activity.getResources().getConfiguration().isNightModeActive();
        Window window = activity.getWindow();
        setLightStatusBarIconColor(window, light);
        setLightNavigationBarIconColor(window, light);
    }

    public static void setLightStatusBarIconColor(Window window, boolean light) {
        int vis = window.getDecorView().getSystemUiVisibility();
        if (light) {
            vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;// 黑色
        } else {
            vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//白色
        }
        window.getDecorView().setSystemUiVisibility(vis);
    }

    public static void setLightNavigationBarIconColor(Window window, boolean light) {
        int vis = window.getDecorView().getSystemUiVisibility();
        if (light) {
            vis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;//黑色
        } else {
            vis &= ~View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;//白色
        }
        window.getDecorView().setSystemUiVisibility(vis);
    }
}

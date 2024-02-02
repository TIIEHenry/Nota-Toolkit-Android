package tiiehenry.ktx.res

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.os.Build
import android.util.TypedValue
import androidx.core.content.ContextCompat

fun Context.getAttrColor(id: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(id, typedValue, true)
    return typedValue.data
}

fun Context.getColorCompat(id: Int): Int {
    return ContextCompat.getColor(this, id)
}

fun Context.getDimen(id: Int): Int {
    return resources.getDimensionPixelSize(id)
}


/** dp和px转换 **/
fun Context.dp2px(dpValue: Float): Int {
    return (dpValue * resources.displayMetrics.density + 0.5f).toInt()
}

fun Context.px2dp(pxValue: Float): Int {
    return (pxValue / resources.displayMetrics.density + 0.5f).toInt()
}

fun Context.sp2px(spValue: Float): Int {
    return (spValue * resources.displayMetrics.scaledDensity + 0.5f).toInt()
}

fun Context.px2sp(pxValue: Float): Int {
    return (pxValue / resources.displayMetrics.scaledDensity + 0.5f).toInt()
}

/** 动态创建Drawable **/
fun Context.createDrawable(color: Int = Color.TRANSPARENT, radius: Float = 0f,
                           strokeColor: Int = Color.TRANSPARENT, strokeWidth: Int = 0,
                           enableRipple: Boolean = true,
                           rippleColor: Int = Color.parseColor("#88999999")): Drawable {
    val content = GradientDrawable().apply {
        setColor(color)
        cornerRadius = radius
        setStroke(strokeWidth, strokeColor)
    }
    if (Build.VERSION.SDK_INT >= 21 && enableRipple) {
        return RippleDrawable(ColorStateList.valueOf(rippleColor), content, null)
    }
    return content
}
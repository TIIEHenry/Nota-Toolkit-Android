package tiiehenry.ktx.content

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager
import androidx.core.content.ContextCompat

fun Context.getWidth(): Int {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val outMetrics = DisplayMetrics()
    wm.defaultDisplay.getMetrics(outMetrics)
    return outMetrics.widthPixels
}

fun Context.getHeight(): Int {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val outMetrics = DisplayMetrics()
    wm.defaultDisplay.getMetrics(outMetrics)
    return outMetrics.heightPixels
}

fun Context.getAttrColor(id: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(id, typedValue, true)
    return typedValue.data
}

fun Context.getColorCompat(id: Int): Int {
    return ContextCompat.getColor(this, id)
}
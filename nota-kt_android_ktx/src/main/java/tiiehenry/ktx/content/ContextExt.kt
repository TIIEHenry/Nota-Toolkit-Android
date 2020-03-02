package tiiehenry.ktx.content

import android.content.Context
import android.os.Message
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import nota.android.toolkit.tiiehenry_ktx.R

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

fun Context.getDimen(id: Int): Int {
    return resources.getDimensionPixelSize(id);
}


fun Context.toast(id: Int) {
    toast(getString(id))
}

fun Context.toast(a: Any) {
    toast(a.toString())
}

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

package tiiehenry.ktx

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable

fun View.inflate(@LayoutRes resource: Int, root: ViewGroup?): View? {
    return LayoutInflater.from(context).inflate(resource, root)
}

fun View.inflate(@LayoutRes resource: Int, root: ViewGroup?, attachToRoot: Boolean): View? {
    return LayoutInflater.from(context).inflate(resource, root, attachToRoot)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}
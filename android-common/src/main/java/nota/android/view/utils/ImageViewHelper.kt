package nota.android.view.utils

import android.graphics.Bitmap
import android.widget.ImageView

object ImageViewHelper {

    fun adjustHeight(imageView: ImageView, bitmap: Bitmap) {
        imageView.post {
            val width = imageView.width
            // 计算高度以保持比例
            val height = (bitmap.height.toFloat() / bitmap.width * width).toInt()
            // 设置 imageView 的高度
            imageView.layoutParams.height = height
            imageView.requestLayout()
        }
    }

    fun adjustHeight(imageView: ImageView, bitmap: Bitmap, requestLayout: () -> Unit) {
        imageView.post {
            val width = imageView.width
            // 计算高度以保持比例
            val height = (bitmap.height.toFloat() / bitmap.width * width).toInt()
            // 设置 imageView 的高度
            imageView.layoutParams.height = height
            imageView.requestLayout()
            requestLayout()
        }
    }
}
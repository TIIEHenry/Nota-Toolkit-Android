package nota.android.view.utils

import android.graphics.Outline
import android.graphics.Rect
import android.view.View
import android.view.ViewOutlineProvider

object ViewStyleSetter {
    /**
     * 为View设置圆角效果
     *
     * @param radius 圆角半径
     */
    fun applyRoundCorner(target: View?, radius: Float) {
        if (target == null) {
            return
        }
        target.clipToOutline = true // 用outline裁剪内容区域
        target.outlineProvider = RoundViewOutlineProvider(radius)
    }

    class RoundViewOutlineProvider(
        /**
         * 圆角弧度
         */
        private val mRadius: Float
    ) : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            // 绘制区域
            val selfRect = Rect(0, 0, view.width, view.height)
            outline.setRoundRect(selfRect, mRadius)
        }
    }
}
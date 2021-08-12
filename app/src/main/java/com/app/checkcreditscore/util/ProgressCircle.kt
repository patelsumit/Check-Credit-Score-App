package com.app.checkcreditscore.util

import android.graphics.RectF
import com.app.view.checkcreditscore.R

class ProgressCircle(
    resourceResolver: Resolver
) {
    val backgroundWidth: Float = resourceResolver.getDimension(R.dimen.dim_05) ?: 0f
    var backgroundCenterX: Float = 0f
        private set
    var backgroundCenterY: Float = 0f
        private set

    val progressWidth: Float = resourceResolver.getDimension(R.dimen.sp_m) ?: 0f
    val progressMargin: Int = resourceResolver.getDimensionPixelSize(R.dimen.sp_xs) ?: 0
    var progress: Float = 0f

    val progressRect = RectF()
    val progressStartAngle: Float = 270f
    val progressSweepAngle: Float
        get() = 360f * progress

    var progressRadius = 0f
        private set

    fun onSizeChanged(width: Int, height: Int) {
        val centerX = width.toFloat() / 2
        val centerY = height.toFloat() / 2

        backgroundCenterX = centerX
        backgroundCenterY = centerY

        progressRadius = (width.toFloat() / 2) - progressWidth

        val reducedRadius = progressRadius - progressMargin
        progressRect.set(
            centerX - reducedRadius,
            centerY - reducedRadius,
            centerX + reducedRadius,
            centerY + reducedRadius
        )
    }

    fun onProgressSet(progress: Float) {
        this.progress = progress.coerceIn(0f, 1f)
    }
}
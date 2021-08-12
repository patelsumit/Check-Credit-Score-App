package com.app.checkcreditscore.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.app.view.checkcreditscore.databinding.ErrorLyoutBinding

// Error layout binding
class ErrorLayout : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    var onErrorRetryClick: (() -> Unit)? = null

    private val binding = ErrorLyoutBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.btnTry.setOnClickListener {
            onErrorRetryClick?.invoke()
        }
    }
}
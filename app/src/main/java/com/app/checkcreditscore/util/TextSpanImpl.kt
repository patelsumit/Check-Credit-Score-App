package com.app.checkcreditscore.util

import android.content.Context
import android.text.style.TextAppearanceSpan
import java.lang.ref.WeakReference

class TextSpanImpl(
    context: Context
) : TextSpan {
    private val contextRef: WeakReference<Context> = WeakReference(context)

    override fun createTextAppearanceSpan(appearanceId: Int): TextAppearanceSpan? {
        return contextRef.get()?.let {
            TextAppearanceSpan(it, appearanceId)
        }
    }
}
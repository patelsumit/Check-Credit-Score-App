package com.app.checkcreditscore.util

import android.text.style.TextAppearanceSpan
import androidx.annotation.IdRes

interface TextSpan {
    fun createTextAppearanceSpan(@IdRes appearanceId: Int): TextAppearanceSpan?
}
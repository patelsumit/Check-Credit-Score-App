package com.app.checkcreditscore.view.model

import androidx.annotation.ColorRes
import com.app.view.checkcreditscore.R

enum class UIModelScore(
    val value: Int,
    @ColorRes val colorRes: Int
) {
    NONE(-1, R.color.dark_grey),
    VERY_POOR(1, R.color.red),
    POOR(2, R.color.orange),
    FAIR(3, R.color.yellow),
    GOOD(4, R.color.light_green),
    VERY_GOOD(5, R.color.more_green),
    EXCELLENT(6, R.color.dark_green);

    companion object {
        fun getInt(value: Int): UIModelScore =
            values().firstOrNull { type -> type.value == value } ?: NONE
    }
}
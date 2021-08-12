package com.app.checkcreditscore.util

import androidx.annotation.*

interface Resolver {
    fun getString(@StringRes stringRes: Int, vararg formatArg: Any = emptyArray()): String?

    fun getColor(@ColorRes colorRes: Int): Int?

    fun getDimension(@DimenRes dimenRes: Int): Float?

    @Px
    fun getDimensionPixelSize(@DimenRes dimenRes: Int): Int?

    fun getResourceIdFromAttribute(@AttrRes attrRes: Int): Int?
}
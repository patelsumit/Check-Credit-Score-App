package com.app.checkcreditscore.domain

enum class ScoreStatus(val values: Int) {
    NONE(-1),
    VPOOR(1),
    POOR(2),
    FAIR(3),
    GOOD(4),
    VGOOD(5),
    EXCLT(6);

    companion object {
        fun getScore(value: Int): ScoreStatus =
            values().firstOrNull { type -> type.values == value } ?: NONE
    }
}
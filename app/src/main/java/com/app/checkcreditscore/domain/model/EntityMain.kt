package com.app.checkcreditscore.domain.model

import com.app.checkcreditscore.domain.ScoreStatus

data class EntityMain (
    val score: Int,
    val minScore: Int,
    val maxScore: Int,
    val scoreType: ScoreStatus
)
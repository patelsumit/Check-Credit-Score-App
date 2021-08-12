package com.app.checkcreditscore.view.model.transform

import com.app.checkcreditscore.domain.model.EntityMain
import com.app.checkcreditscore.view.model.UIModel
import com.app.checkcreditscore.view.model.UIModelScore

fun EntityMain.transform(): UIModel {
    val scoreBandType = UIModelScore.getInt(scoreType.values)
    return UIModel(
        score = score,
        maxScore = maxScore,
        progressCirclePercentage = score.toFloat() / maxScore.toFloat(),
        progressCircleColorRes = scoreBandType.colorRes
    )
}
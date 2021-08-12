package com.app.checkcreditscore.domain.model.transform

import com.app.checkcreditscore.domain.ScoreStatus
import com.app.checkcreditscore.domain.model.EntityMain
import com.app.checkcreditscore.domain.model.response.EntityMainResponse
import com.app.checkcreditscore.endpoint.model.ResponseMainTags

fun ResponseMainTags.transform(): EntityMainResponse {
    val creditReportInfo = this@transform.creditReportInfo
    return EntityMainResponse(
        entityMain = EntityMain(
            score = creditReportInfo?.score ?: -1,
            minScore = creditReportInfo?.minScoreValue ?: -1,
            maxScore = creditReportInfo?.maxScoreValue ?: -1,
            scoreType = ScoreStatus.getScore(creditReportInfo?.scoreBand ?: -1)
        )
    ).apply {
        TransResponse.transform(
            response = this@transform,
            entityResponse = this
        )
    }
}
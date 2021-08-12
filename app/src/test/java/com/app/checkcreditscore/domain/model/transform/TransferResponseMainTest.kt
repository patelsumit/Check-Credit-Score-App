package com.app.checkcreditscore.domain.model.transform

import com.app.checkcreditscore.domain.DefaultEntity
import com.app.checkcreditscore.domain.ScoreStatus
import com.app.checkcreditscore.domain.model.response.EntityMainResponse
import com.app.checkcreditscore.endpoint.model.ResponseCreditInfo
import com.app.checkcreditscore.endpoint.model.ResponseMainTags
import org.junit.Assert
import org.junit.Test

class TransferResponseMainTest {
    @Test
    fun transDefaultNullValue() {
        val expectedScore = DefaultEntity.INTEGER
        val expectedMinScore = DefaultEntity.INTEGER
        val expectedMaxCore = DefaultEntity.INTEGER
        val expectedScoreType = ScoreStatus.NONE

        val responseMainTags = ResponseMainTags()
        val actualMainResponse = responseMainTags.transform()
        actualMainResponse.assertValues(
            expectedScore,
            expectedMinScore,
            expectedMaxCore,
            expectedScoreType
        )
    }

    @Test
    fun transNonNullValue() {
        val expectedScore = 512
        val expectedMinScore = 0
        val expectedMaxCore = 700
        val score = 5
        val expectedScoreType = ScoreStatus.getScore(score)

        val responseMainTags = ResponseMainTags(
            creditReportInfo = ResponseCreditInfo(
                score = expectedScore,
                minScoreValue = expectedMinScore,
                maxScoreValue = expectedMaxCore,
                scoreBand = score
            )
        )

        val actualMainResponse = responseMainTags.transform()
        actualMainResponse.assertValues(
            expectedScore,
            expectedMinScore,
            expectedMaxCore,
            expectedScoreType
        )
    }

    private fun EntityMainResponse.assertValues(
        expectedScore: Int,
        expectedMinScore: Int,
        expectedMaxCore: Int,
        expectedScoreType: ScoreStatus
    ) {
        Assert.assertEquals(expectedScore, entityMain.score)
        Assert.assertEquals(expectedMinScore, entityMain.minScore)
        Assert.assertEquals(expectedMaxCore, entityMain.maxScore)
        Assert.assertEquals(expectedScoreType, entityMain.scoreType)
    }
}



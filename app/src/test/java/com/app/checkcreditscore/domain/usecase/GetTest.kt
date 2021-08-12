package com.app.checkcreditscore.domain.usecase

import com.app.checkcreditscore.domain.ScoreStatus
import com.app.checkcreditscore.domain.model.EntityMain
import com.app.checkcreditscore.domain.model.response.EntityMainResponse
import com.app.checkcreditscore.endpoint.model.ResponseCreditInfo
import com.app.checkcreditscore.endpoint.model.ResponseMainTags
import com.app.checkcreditscore.endpoint.repo.Repository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetTest {

    @Test
    fun executeNonNullValue() = runBlocking {
        val scoreValue = 5
        val expectedScoreType = ScoreStatus.getScore(scoreValue)
        val expectedEntityResponse = EntityMainResponse(
            entityMain = EntityMain(
                score = 510,
                minScore = 0,
                maxScore = 700,
                scoreType = expectedScoreType
            )
        )

        val useCase = GetMain.Factory(
            TestRepository(
                ResponseMainTags(
                    creditReportInfo = ResponseCreditInfo(
                        score = expectedEntityResponse.entityMain.score,
                        minScoreValue = expectedEntityResponse.entityMain.minScore,
                        maxScoreValue = expectedEntityResponse.entityMain.maxScore,
                        scoreBand = scoreValue,
                    )
                )
            )
        ).create()
        val actualEntityResponse = useCase.execute()

        Assert.assertEquals(expectedEntityResponse, actualEntityResponse)
    }

    class TestRepository(
        private val response: ResponseMainTags
    ) : Repository {
        override suspend fun getService(): ResponseMainTags = response
    }
}


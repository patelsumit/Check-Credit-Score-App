package com.app.checkcreditscore.repo

import com.app.checkcreditscore.endpoint.model.ResponseCreditInfo
import com.app.checkcreditscore.endpoint.model.ResponseMainTags
import com.app.checkcreditscore.endpoint.repo.Repository
import com.app.checkcreditscore.endpoint.repo.impl.RepositoryImpl
import com.app.checkcreditscore.endpoint.service.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class RepositoryTest {

    @Test
    fun getRepositoryTest() = runBlocking {
        val expectedResponse = ResponseMainTags(
            accountIDVStatus = "PASS",
            creditReportInfo = ResponseCreditInfo(
                score = 525,
                minScoreValue = 0,
                maxScoreValue = 700
            ),
            dashboardStatus = "PASS",
            personaType = "INEXPERIENCED"
        )

        val repository: Repository =
            RepositoryImpl(TestApiService(expectedResponse))
        val actualOverviewResponse = repository.getService()

        Assert.assertEquals(expectedResponse, actualOverviewResponse)
    }

    private class TestApiService(
        private val response: ResponseMainTags
    ) : ApiService {
        override suspend fun getService(): ResponseMainTags = response
    }
}
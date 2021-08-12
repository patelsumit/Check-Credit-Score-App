package com.app.checkcreditscore.endpoint.service

import com.app.checkcreditscore.endpoint.model.ResponseCreditInfo
import com.app.checkcreditscore.endpoint.model.ResponseMainTags
import com.app.checkcreditscore.endpoint.service.impl.EndPointServiceImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class EndPointServiceTest {

    @Test
    fun getBodyMapped() = runBlocking {
        val expectedResponse = ResponseMainTags(
            accountIDVStatus = "PASS",
            creditReportInfo = ResponseCreditInfo(
                score = 510,
                minScoreValue = 0,
                maxScoreValue = 700
            ),
            dashboardStatus = "PASS",
            personaType = "INEXPERIENCED"
        )

        val repository: ApiService = EndPointServiceImpl(
            ApiServiceTest(
                Response.success(expectedResponse)
            )
        )
        val actualResponse = repository.getService()

        Assert.assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun getPropertiesMapped() = runBlocking {
        val expectedStatus = 200
        val expectedMessage = "Response.success()"
        val expectedIsOkay = true

        val repository: ApiService = EndPointServiceImpl(
            ApiServiceTest(
                Response.success(
                    expectedStatus,
                    ResponseMainTags()
                )
            )
        )
        val actualResponse = repository.getService()

        Assert.assertEquals(expectedStatus, actualResponse.status)
        Assert.assertEquals(expectedMessage, actualResponse.message)
        Assert.assertEquals(expectedIsOkay, actualResponse.isOkay)
    }

    private class ApiServiceTest(
        private val response: Response<ResponseMainTags>
    ) : EndPointService {
        override suspend fun getDataFromAPI(): Response<ResponseMainTags> = response
    }
}
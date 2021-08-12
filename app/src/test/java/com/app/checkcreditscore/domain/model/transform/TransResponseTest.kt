package com.app.checkcreditscore.domain.model.transform

import com.app.checkcreditscore.domain.DefaultEntity
import com.app.checkcreditscore.domain.model.response.EntityResponse
import com.app.checkcreditscore.endpoint.model.Response
import org.junit.Assert
import org.junit.Test

class TransResponseTest {

    @Test
    fun transDefaultNullValue() {
        val expectedIsOkay = DefaultEntity.BOOLEAN
        val expectedCode = DefaultEntity.INTEGER
        val expectedMessage = DefaultEntity.STRING
        val response = object : Response() {}
        val actualResponse = object : EntityResponse() {}
        TransResponse.transform(response, actualResponse)
        actualResponse.assertValues(expectedCode, expectedMessage, expectedIsOkay)
    }

    @Test
    fun transNonNullValue() {
        val expectedCode = 200
        val expectedMessage = "OK"
        val expectedIsOkay = true

        val response = object : Response(
            status = expectedCode,
            message = expectedMessage,
            isOkay = expectedIsOkay
        ) {}

        val actualResponse = object : EntityResponse() {}
        TransResponse.transform(response, actualResponse)
        actualResponse.assertValues(expectedCode, expectedMessage, expectedIsOkay)
    }

    private fun EntityResponse.assertValues(
        expectedCode: Int,
        expectedMessage: String,
        expectedIsOkay: Boolean
    ) {
        Assert.assertEquals(expectedCode, status)
        Assert.assertEquals(expectedMessage, message)
        Assert.assertEquals(expectedIsOkay, isOkay)
    }
}

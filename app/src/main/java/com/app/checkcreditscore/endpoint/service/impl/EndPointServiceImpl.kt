package com.app.checkcreditscore.endpoint.service.impl

import com.app.checkcreditscore.endpoint.model.Response
import com.app.checkcreditscore.endpoint.model.ResponseMainTags
import com.app.checkcreditscore.endpoint.service.ApiService
import com.app.checkcreditscore.endpoint.service.EndPointService

// endpoint implementation to apply properties
class EndPointServiceImpl(
    private val endPointService: EndPointService
) : ApiService {

    override suspend fun getService(): ResponseMainTags {
        val response = endPointService.getDataFromAPI()
        return (response.body() ?: ResponseMainTags()).applyBaseProperties(response)
    }

    private inline fun <reified T : Response> Response.applyBaseProperties(response: retrofit2.Response<*>): T =
        apply {
            status = response.code()
            message = response.message()
            isOkay = response.isSuccessful
        } as T
}
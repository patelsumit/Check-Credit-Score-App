package com.app.checkcreditscore.domain.model.transform

import com.app.checkcreditscore.domain.model.response.EntityResponse
import com.app.checkcreditscore.endpoint.model.Response

object TransResponse {
    fun transform(response: Response, entityResponse: EntityResponse) {
        entityResponse.status = response.status ?: -1
        entityResponse.message = response.message ?: String()
        entityResponse.isOkay = response.isOkay ?: false
    }
}
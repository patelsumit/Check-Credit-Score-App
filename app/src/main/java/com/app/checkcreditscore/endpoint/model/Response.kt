package com.app.checkcreditscore.endpoint.model

abstract class Response (
    var status: Int? = null,
    var message: String? = null,
    var isOkay: Boolean? = null
)
package com.app.checkcreditscore.endpoint.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//Response Main tags from endpoint
@JsonClass(generateAdapter = true)
data class ResponseMainTags(
    @Json(name = "accountIDVStatus") val accountIDVStatus: String? = null,
    @Json(name = "creditReportInfo") val creditReportInfo: ResponseCreditInfo? = null,
    @Json(name = "dashboardStatus") val dashboardStatus: String? = null,
    @Json(name = "personaType") val personaType: String? = null,
    @Json(name = "coachingSummary") val coachingSummary: ResponseSummary? = null,
    @Json(name = "augmentedCreditScore") val augmentedCreditScore: Any? = null
) : Response()
package com.app.checkcreditscore.endpoint.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//  response for summary
@JsonClass(generateAdapter = true)
data class ResponseSummary(
    @Json(name = "activeTodo") val activeTodo: Boolean? = null,
    @Json(name = "activeChat") val activeChat: Boolean? = null,
    @Json(name = "numberOfTodoItems") val numberOfTodoItems: Int? = null,
    @Json(name = "numberOfCompletedTodoItems") val numberOfCompletedTodoItems: Int? = null,
    @Json(name = "selected") val selected: Boolean? = null
)
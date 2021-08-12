package com.app.checkcreditscore.endpoint.service

import com.app.checkcreditscore.endpoint.model.ResponseMainTags
import retrofit2.Response
import retrofit2.http.GET

// endpoint method to get data from service
interface EndPointService {
    @GET("endpoint.json")
    suspend fun getDataFromAPI(): Response<ResponseMainTags>
}
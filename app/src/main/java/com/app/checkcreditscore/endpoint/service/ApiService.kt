package com.app.checkcreditscore.endpoint.service

import com.app.checkcreditscore.endpoint.model.ResponseMainTags

interface ApiService {
    suspend fun getService(): ResponseMainTags
}
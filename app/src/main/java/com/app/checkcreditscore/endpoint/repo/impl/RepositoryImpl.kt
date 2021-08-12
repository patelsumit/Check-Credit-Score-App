package com.app.checkcreditscore.endpoint.repo.impl

import com.app.checkcreditscore.endpoint.model.ResponseMainTags
import com.app.checkcreditscore.endpoint.repo.Repository
import com.app.checkcreditscore.endpoint.service.ApiService

class RepositoryImpl (
    private val apiService : ApiService) : Repository {
    override suspend fun getService(): ResponseMainTags {
        return apiService.getService()
    }
}
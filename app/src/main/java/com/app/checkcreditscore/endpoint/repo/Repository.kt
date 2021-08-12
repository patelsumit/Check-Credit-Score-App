package com.app.checkcreditscore.endpoint.repo

import com.app.checkcreditscore.endpoint.model.ResponseMainTags

interface Repository {
    suspend fun getService() : ResponseMainTags
}
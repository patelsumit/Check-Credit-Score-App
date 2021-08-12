package com.app.checkcreditscore.domain.usecase

import com.app.checkcreditscore.domain.model.response.EntityMainResponse
import com.app.checkcreditscore.domain.model.transform.transform
import com.app.checkcreditscore.endpoint.repo.Repository

class GetMain private constructor(
    private val repository: Repository
) : Base<EntityMainResponse>() {

    override suspend fun doWork(): EntityMainResponse = repository.getService().transform()

    class Factory(
        private val repository: Repository
    ) : UseCaseFactory<EntityMainResponse> {
        override fun create(): UseCase<EntityMainResponse> = GetMain(repository)
    }
}
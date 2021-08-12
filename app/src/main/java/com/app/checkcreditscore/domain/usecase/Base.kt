package com.app.checkcreditscore.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class Base<out T> : UseCase<T> {
    override suspend fun execute(): T = withContext(Dispatchers.IO) {
        doWork()
    }

    protected abstract suspend fun doWork(): T
}
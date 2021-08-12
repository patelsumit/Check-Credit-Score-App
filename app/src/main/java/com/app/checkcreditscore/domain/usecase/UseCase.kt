package com.app.checkcreditscore.domain.usecase

interface UseCase<out T> {
    suspend fun execute(): T
}

interface UseCaseFactory<out T> {
    fun create(): UseCase<T>
}
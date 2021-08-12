package com.app.checkcreditscore.domain.di

import com.app.checkcreditscore.domain.model.response.EntityMainResponse
import com.app.checkcreditscore.domain.usecase.GetMain
import com.app.checkcreditscore.domain.usecase.UseCaseFactory
import com.app.checkcreditscore.endpoint.repo.Repository
import com.app.checkcreditscore.endpoint.repo.impl.RepositoryImpl
import com.app.checkcreditscore.endpoint.service.ApiService
import com.app.checkcreditscore.endpoint.service.EndPointService
import com.app.checkcreditscore.endpoint.service.impl.EndPointServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class Module {
    @Singleton
    @Provides
    fun provideMoshiConvertFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideUseCaseFactory(repository: Repository): UseCaseFactory<EntityMainResponse> {
        return GetMain.Factory(repository)
    }

    @Singleton
    @Provides
    fun provideApiOverviewService(retrofit: Retrofit): EndPointService {
        return retrofit.create(EndPointService::class.java)
    }

    @Singleton
    @Provides
    fun provideApiService(apiServiceOverview: EndPointService): ApiService {
        return EndPointServiceImpl(apiServiceOverview)
    }

    @Singleton
    @Provides
    fun provideOverviewRepository(apiService: ApiService): Repository{
        return RepositoryImpl(apiService)
    }
}
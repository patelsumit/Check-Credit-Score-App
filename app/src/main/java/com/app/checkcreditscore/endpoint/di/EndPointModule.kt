package com.app.checkcreditscore.endpoint.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/*  Service Endpoint
* */
@Module
@InstallIn(ApplicationComponent::class)
object EndPointModule {
    @Singleton
    @Provides
    fun getEndPointInstance(moshiConverterFactory: MoshiConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://android-interview.s3.eu-west-2.amazonaws.com/")
            .addConverterFactory(moshiConverterFactory)
            .build()
    }
}
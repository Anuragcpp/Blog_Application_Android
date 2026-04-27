package com.content.blogapplication.auth.di

import com.content.blogapplication.auth.data.apiService.AuthApiService
import com.content.blogapplication.util.network.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Singleton
    @Provides
    fun provideAuthApiService() : AuthApiService {
        return RetrofitInstance
            .getRetrofitInstance()
            .create(
                AuthApiService::class.java
            )
    }

}
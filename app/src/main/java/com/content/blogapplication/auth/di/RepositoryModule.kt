package com.content.blogapplication.auth.di

import com.content.blogapplication.auth.data.apiService.AuthApiService
import com.content.blogapplication.auth.data.repository.AuthRepoRepositoryImpl
import com.content.blogapplication.auth.data.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(
        authApiService: AuthApiService
    ) : AuthRepository {
        return AuthRepoRepositoryImpl(authApiService)
    }

}
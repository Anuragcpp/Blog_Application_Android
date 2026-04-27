package com.content.blogapplication.auth.di

import com.content.blogapplication.auth.data.repository.AuthRepository
import com.content.blogapplication.auth.viewmodel.AuthViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthViewmodelModule {

    @Provides
    @Singleton
    fun provideAuthViewModel(
        authRepository: AuthRepository
    ) : AuthViewModel {
        return AuthViewModel(authRepository)
    }
}
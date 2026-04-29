package com.content.blogapplication.mainactivty.di

import com.content.blogapplication.mainactivty.viewModel.MainViewModel
import com.content.blogapplication.util.sharedPreference.SharedPreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainViewmodelModule {

    @Provides
    @Singleton
    fun provideMainViewModel(
        sharedPreferenceManager: SharedPreferenceManager
    ) : MainViewModel {
        return MainViewModel(sharedPreferenceManager)
    }
}
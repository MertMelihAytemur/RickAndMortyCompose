package com.mermela.rickandmortycompose.feature.home.data.di

import com.mermela.rickandmortycompose.feature.home.data.service.HomeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Singleton
    @Provides
    fun provideHomeService(retrofit : Retrofit) : HomeService {
        return retrofit.create(HomeService::class.java)
    }
}
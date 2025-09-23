package com.mermela.rickandmortycompose.feature.home.data.di

import com.mermela.rickandmortycompose.core.network.services.CharacterService
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
    fun provideCharacterService(retrofit : Retrofit) : CharacterService {
        return retrofit.create(CharacterService::class.java)
    }
}
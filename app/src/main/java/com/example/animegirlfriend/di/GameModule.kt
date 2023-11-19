package com.example.animegirlfriend.di

import com.example.animegirlfriend.game.core.GameController
import com.example.animegirlfriend.game.core.IGameController
import com.example.animegirlfriend.utils.SharedPreferencesGameData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GameModule {

    @Provides
    @Singleton
    fun provideGameController(sharedPreferencesGameData: SharedPreferencesGameData): IGameController{
        return GameController(sharedPreferencesGameData)
    }


}
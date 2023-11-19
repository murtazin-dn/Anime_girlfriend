package com.example.animegirlfriend.di

import android.content.Context
import com.example.animegirlfriend.utils.SharedPreferencesGameData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideSharedPreferencesGameData(@ApplicationContext context: Context) : SharedPreferencesGameData{
        return SharedPreferencesGameData(context)
    }

}
package com.example.animegirlfriend.di

import com.example.animegirlfriend.domain.game.AddObserverUseCase
import com.example.animegirlfriend.domain.game.ClickUseCase
import com.example.animegirlfriend.domain.game.RemoveObserverUseCase
import com.example.animegirlfriend.domain.game.SetGirlUseCase
import com.example.animegirlfriend.domain.game.UpdateCharUseCase
import com.example.animegirlfriend.domain.game.UpdateClickUseCase
import com.example.animegirlfriend.game.core.IGameController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {

    @Provides
    fun provideAddObserverUseCase(controller: IGameController): AddObserverUseCase{
        return AddObserverUseCase(controller)
    }
    @Provides
    fun provideRemoveObserverUseCase(controller: IGameController): RemoveObserverUseCase {
        return RemoveObserverUseCase(controller)
    }
    @Provides
    fun provideClickUseCase(controller: IGameController): ClickUseCase{
        return ClickUseCase(controller)
    }
    @Provides
    fun provideSetGirlUseCase(controller: IGameController): SetGirlUseCase{
        return SetGirlUseCase(controller)
    }
    @Provides
    fun provideUpdateCharUseCase(controller: IGameController): UpdateCharUseCase{
        return UpdateCharUseCase(controller)
    }
    @Provides
    fun provideUpdateClickUseCase(controller: IGameController): UpdateClickUseCase{
        return UpdateClickUseCase(controller)
    }
}
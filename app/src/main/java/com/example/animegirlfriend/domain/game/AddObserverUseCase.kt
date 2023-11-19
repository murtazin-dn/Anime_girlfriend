package com.example.animegirlfriend.domain.game

import com.example.animegirlfriend.game.core.IGameController
import com.example.animegirlfriend.game.model.GameDataModel

class AddObserverUseCase(private val controller: IGameController) {
    fun execute(action: (GameDataModel) -> Unit) {
        controller.addObserver(action)
    }
}
package com.example.animegirlfriend.domain.game

import com.example.animegirlfriend.game.core.IGameController

class UpdateCharUseCase(private val controller: IGameController) {
    fun execute() = controller.updateChar()
}
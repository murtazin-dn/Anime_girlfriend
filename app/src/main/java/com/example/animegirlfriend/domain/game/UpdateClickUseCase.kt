package com.example.animegirlfriend.domain.game

import com.example.animegirlfriend.game.core.IGameController

class UpdateClickUseCase(private val controller: IGameController) {
    fun execute() = controller.updateClick()
}
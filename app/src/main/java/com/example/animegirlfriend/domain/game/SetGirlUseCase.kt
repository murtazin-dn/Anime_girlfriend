package com.example.animegirlfriend.domain.game

import com.example.animegirlfriend.game.core.IGameController

class SetGirlUseCase(private val controller: IGameController) {
    fun execute(index: Int) = controller.setGirl(index)
}
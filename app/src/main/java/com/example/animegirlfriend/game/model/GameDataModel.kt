package com.example.animegirlfriend.game.model

import kotlinx.serialization.Serializable

@Serializable
data class GameDataModel(
    val money: Int,
    val priceUpClick: Int,
    val updateClickCount: Int,
    val clickAmount: Int,
    val priceUpGirl: Int,
    val girlAmount: Int,
    val curGirl: Int,
    val girls: List<GirlModel>
)

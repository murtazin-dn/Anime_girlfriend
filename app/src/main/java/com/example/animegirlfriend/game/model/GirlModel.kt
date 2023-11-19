package com.example.animegirlfriend.game.model

import kotlinx.serialization.Serializable

@Serializable
data class GirlModel(
//    val image: Int,
    val name: String,
    var isOpen: Boolean = false
)

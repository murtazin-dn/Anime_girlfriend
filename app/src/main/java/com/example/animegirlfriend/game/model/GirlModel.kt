package com.example.animegirlfriend.game.model

import com.example.animegirlfriend.ui.characters.adapter.RvGirlModel
import kotlinx.serialization.Serializable

@Serializable
data class GirlModel(
//    val image: Int,
    val img: Int,
    var isOpen: Boolean = false
)
fun GirlModel.toRvGirlModel(id: Int, isSelected: Boolean) = RvGirlModel(
    id = id,
    isOpen = isOpen,
    img = img,
    isSelected = isSelected
)


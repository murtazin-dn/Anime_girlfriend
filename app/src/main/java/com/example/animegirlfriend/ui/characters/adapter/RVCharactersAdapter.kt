package com.example.animegirlfriend.ui.characters.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animegirlfriend.databinding.ItemCardBinding
import com.example.animegirlfriend.game.model.GirlModel


class RVCharactersAdapter(private val action: (Int) -> Unit)
    : RecyclerView.Adapter<RVCharactersAdapter.CharacterViewHolder>() {

    var girls: List<RvGirlModel> = listOf()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }
    override fun getItemCount() = girls.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val girl = girls[position]
        holder.bind(girl)
    }
    inner class CharacterViewHolder(itemBinding: ItemCardBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        private val binding = itemBinding

        fun bind(girl: RvGirlModel){
            if (girl.isOpen){
                val color = Color.TRANSPARENT
                binding.imgGirl.setColorFilter(color)

                binding.imgGirl.setOnClickListener{
                    action.invoke(girl.id)
                }
            } else {
                val color = Color.parseColor("#000000") //The color u want
                binding.imgGirl.setColorFilter(color)
                binding.imgGirl.setOnClickListener {  }
            }
            binding.imgGirl.setImageResource(girl.img)
        }

    }
}


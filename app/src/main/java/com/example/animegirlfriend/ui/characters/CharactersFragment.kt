package com.example.animegirlfriend.ui.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.animegirlfriend.databinding.FragmentCharactersBinding
import com.example.animegirlfriend.ui.characters.adapter.RVCharactersAdapter
import com.example.animegirlfriend.ui.characters.adapter.RvGirlModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding get() = _binding!!

    private val viewModel: CharactersViewModel by viewModels()

    private lateinit var adapter: RVCharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RVCharactersAdapter {
            viewModel.setGirl(it)
            findNavController().popBackStack()
        }
        viewModel.gameDataLiveData.observe(viewLifecycleOwner){ gameData ->
            val girls = mutableListOf<RvGirlModel>()
            gameData.girls.forEachIndexed{i, girl ->
                girls.add(RvGirlModel(
                    id = i,
                    isOpen = girl.isOpen,
                    img = girl.img,
                    isSelected = (i == gameData.curGirl)
                ))
            }
            adapter.girls = girls
            binding.rvCharacters.scrollToPosition(gameData.curGirl)
        }
        binding.rvCharacters.adapter = adapter
        binding.rvCharacters.layoutManager = LinearLayoutManager(requireContext()).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        PagerSnapHelper().attachToRecyclerView(binding.rvCharacters)
    }


}
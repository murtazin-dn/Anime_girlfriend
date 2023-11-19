package com.example.animegirlfriend.ui.mainfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.animegirlfriend.game.core.GameController
import com.example.animegirlfriend.databinding.FragmentMainBinding
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.gameDataLiveData.observe(viewLifecycleOwner) {
            with(it){
                println(this)
                binding.tvMoney.text = money.toString()
                binding.tvUpdateCharAmount.text = priceUpGirl.toString()
                binding.tvUpdateClickAmount.text = priceUpClick.toString()
                binding.tvLevelChar.text = girls.count {girl -> girl.isOpen }.toString()
                binding.tvLevelClick.text = updateClickCount.toString()
            }
        }
        binding.imgGirl.setOnClickListener {
            viewModel.click()
        }
        binding.btnUpdateClick.setOnClickListener {
            viewModel.updateClick()
        }
        binding.btnUpdateChar.setOnClickListener {
            viewModel.updateChar()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
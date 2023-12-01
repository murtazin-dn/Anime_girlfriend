package com.example.animegirlfriend.ui.mainfragment

import android.animation.ValueAnimator
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.animegirlfriend.R
import com.example.animegirlfriend.game.core.GameController
import com.example.animegirlfriend.databinding.FragmentMainBinding
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    private val viewModel: MainFragmentViewModel by viewModels()

    private lateinit var playerBubble: MediaPlayer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPlayers()
        setObservers()
        setListeners()

    }

    private fun setPlayers() {
        playerBubble = MediaPlayer.create(requireContext(), R.raw.snd_bubble1).apply {
            setOnPreparedListener { it.start() }
//            setOnErrorListener { mediaPlayer, i, i2 ->  println("pisapopa") }
        }
    }

    private fun setListeners() {
        binding.imgGirl.setOnClickListener {
            viewModel.click()
            animationStart(it)
            playBubble()
        }
        binding.btnUpdateClick.setOnClickListener {
            viewModel.updateClick()
            animationStart(it)
            playBubble()
        }
        binding.btnUpdateChar.setOnClickListener {
            viewModel.updateChar()
            animationStart(it)
            playBubble()
        }
        binding.btnChars.setOnClickListener {
            animationStart(it)
            playBubble()
            findNavController().navigate(R.id.action_mainFragment_to_charactersFragment)
        }
    }

    private fun setObservers() {
        viewModel.gameDataLiveData.observe(viewLifecycleOwner) {
            with(it) {
                println(this)
                binding.imgGirl.setImageResource(girls[curGirl].img)
                binding.tvMoney.text = money.toString()
                binding.tvUpdateCharAmount.text = priceUpGirl.toString()
                binding.tvUpdateClickAmount.text = priceUpClick.toString()
                binding.tvLevelChar.text = girls.count { girl -> girl.isOpen }.toString()
                binding.tvLevelClick.text = updateClickCount.toString()
            }
        }
    }

    private fun playBubble() = with(playerBubble){
        try{
            stop()
            prepareAsync()
        }catch (e: Exception){}
    }



    private fun animationStart(view: View){
        val animator = ValueAnimator.ofFloat(1F, 0.97F)
        animator.addUpdateListener {
            val value = it.animatedValue as Float
            view.scaleX = value
            view.scaleY = value
        }
        animator.interpolator = LinearInterpolator()
        animator.duration = 100
        animator.repeatMode = ValueAnimator.REVERSE
        animator.repeatCount = 1
        animator.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
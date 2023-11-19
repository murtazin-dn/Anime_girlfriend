package com.example.animegirlfriend.ui.mainfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animegirlfriend.domain.game.AddObserverUseCase
import com.example.animegirlfriend.domain.game.ClickUseCase
import com.example.animegirlfriend.domain.game.RemoveObserverUseCase
import com.example.animegirlfriend.domain.game.SetGirlUseCase
import com.example.animegirlfriend.domain.game.UpdateCharUseCase
import com.example.animegirlfriend.domain.game.UpdateClickUseCase
import com.example.animegirlfriend.game.core.IGameController
import com.example.animegirlfriend.game.model.GameDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val addObserverUseCase: AddObserverUseCase,
    private val removeObserverUseCase: RemoveObserverUseCase,
    private val clickUseCase: ClickUseCase,
    private val updateCharUseCase: UpdateCharUseCase,
    private val updateClickUseCase: UpdateClickUseCase
): ViewModel() {

    private val TAG = this.javaClass.name.toString()

    private val _gameDataLiveData = MutableLiveData<GameDataModel>()
    val gameDataLiveData: LiveData<GameDataModel> get() = _gameDataLiveData

    private fun updateData(gameData: GameDataModel){
        _gameDataLiveData.value = gameData
    }

    init {
        addObserverUseCase.execute(::updateData)
        removeObserverUseCase.execute(::updateData)
    }

    fun click() {
        clickUseCase.execute()
        Log.e(TAG, "click")
    }
    fun updateChar() = updateCharUseCase.execute()
    fun updateClick() = updateClickUseCase.execute()
}
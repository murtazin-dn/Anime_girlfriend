package com.example.animegirlfriend.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animegirlfriend.domain.game.AddObserverUseCase
import com.example.animegirlfriend.domain.game.RemoveObserverUseCase
import com.example.animegirlfriend.domain.game.SetGirlUseCase
import com.example.animegirlfriend.game.model.GameDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val addObserverUseCase: AddObserverUseCase,
    private val removeObserverUseCase: RemoveObserverUseCase,
    private val setGirlUseCase: SetGirlUseCase
) : ViewModel() {

    private val _gameDataLiveData = MutableLiveData<GameDataModel>()
    val gameDataLiveData: LiveData<GameDataModel> get() = _gameDataLiveData

    private fun updateData(gameData: GameDataModel){
        _gameDataLiveData.value = gameData
    }

    fun setGirl(index: Int) = setGirlUseCase.execute(index)

    init {
        addObserverUseCase.execute(::updateData)
    }

    override fun onCleared() {
        removeObserverUseCase.execute(::updateData)
        super.onCleared()
    }


}
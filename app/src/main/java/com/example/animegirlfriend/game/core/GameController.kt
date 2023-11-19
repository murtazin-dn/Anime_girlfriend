package com.example.animegirlfriend.game.core

import android.util.Log
import com.example.animegirlfriend.game.model.GameDataModel
import com.example.animegirlfriend.game.model.GirlModel
import com.example.animegirlfriend.utils.SharedPreferencesGameData
import javax.inject.Inject
import kotlin.properties.Delegates


interface IGameController {
    fun addObserver(action: (GameDataModel) -> Unit)
    fun removeObserver(action: (GameDataModel) -> Unit)
    fun updateClick()
    fun click()
    fun updateChar()
    fun setGirl(index: Int)
}

class GameController @Inject constructor(
    private val sharedPreferencesGameData: SharedPreferencesGameData
) : IGameController {

    private val TAG = this.toString()

    private var money = 0
    private var priceUpClick = 0
    private var updateClickCount = 0
    private val updateClickRate = 1.2
    private var clickAmount = 0

    private var priceUpGirl = 0
    private var girls = listOf<GirlModel>()
    private val updateGirlRate = 5
    private var curGirl = 0

    private val observers = mutableListOf<(GameDataModel) -> Unit >()

    private var newGameData: GameDataModel by Delegates.observable(
        GameDataModel(
            money = money,
            priceUpClick = priceUpClick,
            updateClickCount = updateClickCount,
            clickAmount = clickAmount,
            priceUpGirl = priceUpGirl,
            girlAmount = girls.count { it.isOpen },
            curGirl = curGirl,
            girls = girls
        )
    ) { _, _, newValue ->
        observers.forEach { it(newValue) }
    }

    override fun addObserver(action: (GameDataModel) -> Unit){
        observers.add(action)
        raiseData()
    }

    override fun removeObserver(action: (GameDataModel) -> Unit){
        observers.remove(action)
    }


    private fun isFirstSession(): Boolean = !sharedPreferencesGameData.containsGameData()


    init {
        if(isFirstSession()){
            loadStartingValues()
            raiseData()
        }else{
            loadGame()
        }
    }

    private fun saveGame() {
        sharedPreferencesGameData.saveGameData(newGameData)
    }

    private fun loadGame() {
        val gameData = sharedPreferencesGameData.loadGameData()
        money = gameData.money
        priceUpClick = gameData.priceUpClick
        updateClickCount = gameData.updateClickCount
        clickAmount = gameData.clickAmount
        priceUpGirl = gameData.priceUpGirl
        girls = gameData.girls
        curGirl = gameData.curGirl

        newGameData = gameData
    }

    private fun loadStartingValues() {
        money = 0
        priceUpClick = 100
        updateClickCount = 0
        clickAmount = 1

        priceUpGirl = 100
        girls = listOf(
            GirlModel("1", true),
            GirlModel("2"),
            GirlModel("3"),
            GirlModel("4"),
            GirlModel("5"),
            GirlModel("6"),
            GirlModel("7"),
            GirlModel("8"),
            GirlModel("9"),
            GirlModel("10"),
            GirlModel("11"),
            GirlModel("12"),
            GirlModel("13"),
            GirlModel("14"),
            GirlModel("15"),
            GirlModel("16"),
            GirlModel("17")
        )
    }

    override fun updateClick(){
        if(money < priceUpClick) return
        money -= priceUpClick
        priceUpClick = (priceUpClick * updateClickRate).toInt()
        clickAmount = priceUpClick / 55
        updateClickCount += 1
        raiseData()
    }

    override fun click(){
        Log.e(TAG, "click")
        money += clickAmount

        raiseData()
    }

    override fun updateChar(){
        if(money < priceUpGirl) return
        val firstNotOpenGirl = girls.find { !it.isOpen } ?: return
        val firstNotOpenGirlIndex = girls.indexOf(firstNotOpenGirl)
        money -= priceUpGirl
        priceUpGirl *= updateGirlRate
        val newGirls = girls.toMutableList()
        newGirls[firstNotOpenGirlIndex].isOpen = true
        girls = newGirls
        curGirl = firstNotOpenGirlIndex

        raiseData()
    }

    override fun setGirl(index: Int){
        val girl = girls.getOrNull(index)
            ?: throw RuntimeException("the index of the girl is outside the boundaries of the list")
        if(!girl.isOpen) throw RuntimeException("girl is not open")
        curGirl = index
        raiseData()
    }

    private fun raiseData(){
        newGameData = GameDataModel(
            money = money,
            priceUpClick = priceUpClick,
            updateClickCount = updateClickCount,
            clickAmount = clickAmount,
            priceUpGirl = priceUpGirl,
            girlAmount = girls.count { it.isOpen },
            curGirl = curGirl,
            girls = girls
        )
        saveGame()
    }
}
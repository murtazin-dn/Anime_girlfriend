package com.example.animegirlfriend.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.animegirlfriend.game.model.GameDataModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SharedPreferencesGameData(context: Context) {

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    fun saveGameData(data: GameDataModel){
        put(GAME_DATA, Json.encodeToString(data))
    }

    fun loadGameData(): GameDataModel{
        return Json.decodeFromString<GameDataModel>(get(GAME_DATA, String::class.java))
    }

    fun containsGameData(): Boolean{
        return sharedPref.contains(GAME_DATA)
    }

    private fun <T> get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> sharedPref.getString(key, "")
            Boolean::class.java -> sharedPref.getBoolean(key, false)
            Float::class.java -> sharedPref.getFloat(key, -1f)
            Double::class.java -> sharedPref.getFloat(key, -1f)
            Int::class.java -> sharedPref.getInt(key, -1)
            Long::class.java -> sharedPref.getLong(key, -1L)
            else -> null
        } as T

    private fun <T> put(key: String, data: T) {
        val editor = sharedPref.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

    companion object{
        const val PREF = "pref"
        const val GAME_DATA = "game_data"
    }
}
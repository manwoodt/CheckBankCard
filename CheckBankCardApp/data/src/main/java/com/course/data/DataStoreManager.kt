package com.course.data

import android.content.Context

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.course.domain.model.BinHistoryItem
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore("bin_history")

class DataStoreManager(private val context: Context) {

    private val dataStore = context.dataStore
    private val BIN_HISTORY = stringPreferencesKey("bin_history")

    suspend fun saveBinHistory(binHistory: List<BinHistoryItem>) {
        val json = Gson().toJson(binHistory)
        dataStore.edit { preferences ->
            preferences[BIN_HISTORY] = json
            println("Saved history: $json")
        }
    }

     fun loadBinHistory(): Flow<List<BinHistoryItem>> {
        return dataStore.data.map { preferences ->
            val json = preferences[BIN_HISTORY] ?: "[]"
            println("Loaded history: $json")
            Gson().fromJson(json, Array<BinHistoryItem>::class.java).toList()
        }
    }

}
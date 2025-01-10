package com.course.data

import android.content.Context

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.course.domain.model.BinHistoryItem
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


private val Context.dataStore by preferencesDataStore("bin_history")

class DataStoreManager(private val context: Context) {

    private val dataStore = context.dataStore
    private val BIN_HISTORY = stringPreferencesKey("bin_history")

    private val _binHistoryLiveData = MutableLiveData<List<BinHistoryItem>>()
    val binHistoryLiveData: LiveData<List<BinHistoryItem>> get() = _binHistoryLiveData

    suspend fun saveBinHistory(binHistory: List<BinHistoryItem>) {
        val binHistoryJson = Gson().toJson(binHistory)
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[BIN_HISTORY] = binHistoryJson
            }
        }
    }

    suspend fun loadBinHistory() {
        withContext(Dispatchers.IO) {
            val preferences = dataStore.data.first()
            val binHistoryJson = preferences[BIN_HISTORY] ?: "[]"
            val binHistory =
                Gson().fromJson(binHistoryJson, Array<BinHistoryItem>::class.java).toList()
            _binHistoryLiveData.postValue(binHistory)
        }
    }
}
package com.course.checkbankcard.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.data.DataStoreManager
import com.course.domain.model.BinHistoryItem
import kotlinx.coroutines.launch

class HistoryScreenViewModel(
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {

    private val _binHistory = MutableLiveData<List<BinHistoryItem>>()
    val binHistory: LiveData<List<BinHistoryItem>> get() = _binHistory

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    init {
        loadBinHistory()
    }

    private fun loadBinHistory() {
        viewModelScope.launch {
            try {
                dataStoreManager.loadBinHistory().collect { history ->
                    _binHistory.postValue(history)
                }
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка загрузки истории: ${e.message}"
            }
        }
    }

    fun addBinHistory(binHistoryItem: BinHistoryItem) {
        viewModelScope.launch {
            try {
                val currentHistory = _binHistory.value.orEmpty()
                // потом заменить
                val updatedHistory = if (!currentHistory.any { it.binNumber == binHistoryItem.binNumber }) {
                    currentHistory + binHistoryItem
                } else {
                    currentHistory
                }
                dataStoreManager.saveBinHistory(updatedHistory)
                _binHistory.postValue(updatedHistory)
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка добавления в историю: ${e.message}"
            }
        }
    }

}

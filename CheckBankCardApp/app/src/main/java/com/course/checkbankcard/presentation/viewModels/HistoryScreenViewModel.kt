package com.course.checkbankcard.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.data.DataStoreManager
import com.course.domain.model.BinHistoryItem
import kotlinx.coroutines.launch

class HistoryScreenViewModel(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _binHistory = MutableLiveData<List<BinHistoryItem>>()
    val binHistory: LiveData<List<BinHistoryItem>> get() = _binHistory

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    // Загружаем историю
    fun loadBinHistory() {
        viewModelScope.launch {
            try {
                dataStoreManager.loadBinHistory()
                dataStoreManager.binHistoryLiveData.observeForever { history ->
                    _binHistory.value = history
                }
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка загрузки истории: ${e.message}"
            }
        }
    }

    // Добавляем запись в историю
    fun addBinHistory(binHistoryItem: BinHistoryItem) {
        viewModelScope.launch {
            try {
                val currentHistory = _binHistory.value.orEmpty()
                val updatedHistory = currentHistory + binHistoryItem
                dataStoreManager.saveBinHistory(updatedHistory)
                _binHistory.value = updatedHistory
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка добавления в историю: ${e.message}"
            }
        }
    }
}

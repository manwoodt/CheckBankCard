package com.course.checkbankcard.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.data.DataStoreManager
import com.course.domain.model.BinHistoryItem
import com.course.domain.usecases.GetBinHistoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HistoryScreenViewModel(
    private val getBinHistoryUseCase: GetBinHistoryUseCase
) : ViewModel() {

    private val _binHistory = MutableStateFlow<List<BinHistoryItem>>(emptyList())
    val binHistory: StateFlow<List<BinHistoryItem>> get() = _binHistory

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    init {
        loadBinHistory()
    }

    private fun loadBinHistory() {
        viewModelScope.launch {
            getBinHistoryUseCase()
                .catch { e ->
                    _errorMessage.value = "Ошибка загрузки истории: ${e.message}"
                }
                .collect{
                    historyList->
                    _binHistory.value = historyList
                }
//            try {
//                dataStoreManager.loadBinHistory().collect { history ->
//                    _binHistory.postValue(history)
//                }
//            } catch (e: Exception) {
//                _errorMessage.value = "Ошибка загрузки истории: ${e.message}"
//            }
        }
    }

//        fun addBinHistory(binHistoryItem: BinHistoryItem) {
//            viewModelScope.launch {
//                try {
//                    val currentHistory = _binHistory.value.orEmpty()
//                    // потом заменить
//                    val updatedHistory = if (!currentHistory.any { it.binNumber == binHistoryItem.binNumber }) {
//                        currentHistory + binHistoryItem
//                    } else {
//                        currentHistory
//                    }
//                    dataStoreManager.saveBinHistory(updatedHistory)
//                    _binHistory.postValue(updatedHistory)
//                } catch (e: Exception) {
//                    _errorMessage.value = "Ошибка добавления в историю: ${e.message}"
//                }
//            }
//        }

}

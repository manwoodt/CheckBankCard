package com.course.checkbankcard.presentation.viewModels

import android.util.Log
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
                    Log.d("HistoryScreenViewModel", "History loaded: $history")
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

//    fun addFakeData() {
//        val sampleBinInfo = com.course.domain.model.BinInfo(
//            country = com.course.domain.model.CountryInfo("Россия", 55, 37),
//            scheme = "Visa",
//            bank = com.course.domain.model.BankInfo(
//                "Сбербанк",
//                "www.sberbank.ru",
//                "+7 800 555 55 50",
//                "Москва"
//            )
//        )
//
//        val sampleBinInfo2 = com.course.domain.model.BinInfo(
//            country = com.course.domain.model.CountryInfo("Тм", 55, 37),
//            scheme = "МИР",
//            bank = com.course.domain.model.BankInfo(
//                "Яндекс",
//                "www.sberbank.ru",
//                "+7 800 555 55 50",
//                "Москва"
//            )
//        )
//
//        viewModelScope.launch {
//            val fakeData = listOf(
//                BinHistoryItem("123456", sampleBinInfo),
//                BinHistoryItem("654321", sampleBinInfo2)
//            )
//            dataStoreManager.saveBinHistory(fakeData)
//            _binHistory.value = fakeData
//            Log.d("HistoryScreenViewModel", "Fake data added: $fakeData")
//        }


//    }
}

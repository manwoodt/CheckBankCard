package com.course.checkbankcard.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.domain.model.BinInfo
import com.course.domain.usecases.GetBinInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainScreenViewModel(
    private val getBinInfoUseCase: GetBinInfoUseCase
): ViewModel(){

    private val _binInfo =  MutableStateFlow<BinInfo?>(null)
    val binInfo: StateFlow<BinInfo?> get() = _binInfo

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchBinInfo(bin:String){
        viewModelScope.launch {
            try {
                _binInfo.value = withContext(Dispatchers.IO) {
                    getBinInfoUseCase(bin)
                }
                _errorMessage.value = null
            }
            catch (e:Exception ){
                _errorMessage.value = e.message
                _binInfo.value = null
            }
        }
    }

}

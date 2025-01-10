package com.course.checkbankcard.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.domain.model.BinInfo
import com.course.domain.usecases.GetBinInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainScreenViewModel(
    private val getBinInfoUseCase: GetBinInfoUseCase
): ViewModel(){

    private val _binInfo =  MutableLiveData<BinInfo?>()
    val binInfo: LiveData<BinInfo?> get() = _binInfo

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun fetchBinInfo(bin:String){
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    getBinInfoUseCase(bin)
                }
                _binInfo.postValue(result)
                _errorMessage.value = null
            }
            catch (e:Exception ){
                _errorMessage.value = e.message
                _binInfo.value = null
            }
        }
    }

}

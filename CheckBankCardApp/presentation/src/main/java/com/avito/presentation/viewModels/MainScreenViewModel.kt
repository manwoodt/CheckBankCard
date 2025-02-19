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
import retrofit2.HttpException
import java.io.IOException

class MainScreenViewModel(
    private val getBinInfoUseCase: GetBinInfoUseCase,
) : ViewModel() {

    private val _binInfo = MutableStateFlow<BinInfo?>(null)
    val binInfo: StateFlow<BinInfo?> get() = _binInfo

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchBinInfo(bin: String) {
        viewModelScope.launch {
            try {
                _binInfo.value = withContext(Dispatchers.IO) {
                    getBinInfoUseCase(bin)
                }
                _errorMessage.value = null
            } catch (e: HttpException) {
                _errorMessage.value = when (e.code()) {
                    400 -> "Bad Request: The request was invalid."
                    403 -> "Forbidden: You do not have permission to access this resource."
                    404 -> "Not Found: The requested resource was not found."
                    429 -> "Too Many Requests: Please try again later."
                    500 -> "Internal Server Error: Something went wrong on the server."
                    else -> "HTTP Error: ${e.code()}"
                }
                _binInfo.value = null
            } catch (e: IOException) {
                _errorMessage.value = "Network Error: Please check your internet connection."
                _binInfo.value = null
            } catch (e: Exception) {
                _errorMessage.value = "An unexpected error occurred: ${e.message}"
                _binInfo.value = null
            }
        }
    }

}

package com.course.checkbankcard.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.course.checkbankcard.presentation.viewModels.HistoryScreenViewModel
import com.course.checkbankcard.presentation.viewModels.MainScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryScreen(viewModel: HistoryScreenViewModel = koinViewModel()){
    val binHistory by viewModel.binHistory.observeAsState(listOf())

    Column {
        Text(text = "История запросов")
        binHistory.forEach { binHistoryItem ->
            Text(text = "BIN: ${binHistoryItem.binNumber}")
            Text(text = "Страна: ${binHistoryItem.binInfo.country.name}")
            Text(text = "Тип карты: ${binHistoryItem.binInfo.scheme}")
            Text(text = "Банк: ${binHistoryItem.binInfo.bank.name}")
            Text(text = "Город: ${binHistoryItem.binInfo.bank.city}")
            Text(text = "Телефон: ${binHistoryItem.binInfo.bank.phone}")
            Text(text = "Сайт: ${binHistoryItem.binInfo.bank.url}")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun OldRequests(){

}
package com.course.checkbankcard.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.course.checkbankcard.presentation.viewModels.HistoryScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryScreen(
    navController: NavController,
    viewModel: HistoryScreenViewModel = koinViewModel()
) {
    val binHistory by viewModel.binHistory.observeAsState(listOf())
    val errorMessage by viewModel.errorMessage.observeAsState(null)


    Column(modifier = Modifier.padding(16.dp)) {
        Text("История BIN-кодов", modifier = Modifier.padding(bottom = 16.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f) // Это позволяет списку занимать доступное пространство, оставляя место для кнопки
                .fillMaxWidth()
        ) {
            items(binHistory) { item ->
                Column(modifier = Modifier.padding(bottom = 16.dp)) {
                    Text(text = "BIN: ${item.binNumber}")
                    ShowInformationOfCard(item.binInfo)
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }

        errorMessage?.let {
            Text(text = "Ошибка: $it", color = androidx.compose.ui.graphics.Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Назад")
        }
    }

}

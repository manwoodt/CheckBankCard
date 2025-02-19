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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.avito.presentation.R
import com.course.checkbankcard.presentation.viewModels.HistoryScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryScreen(
    navController: NavController,
    viewModel: HistoryScreenViewModel = koinViewModel(),
) {
    val binHistory by viewModel.binHistory.collectAsState(initial = emptyList())
    val errorMessage by viewModel.errorMessage.collectAsState(null)


    Column(modifier = Modifier.padding(16.dp)) {
        Text(stringResource(R.string.history_bin), modifier = Modifier.padding(bottom = 16.dp, top = 16.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(binHistory) { item ->
                Column(modifier = Modifier.padding(bottom = 16.dp)) {
                    LabeledText(R.string.bin, item.binNumber)
                    ShowInformationOfCard(item.binInfo)
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }

        errorMessage?.let {
            Text(
                text = stringResource(R.string.error_message, it),
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.back))
        }
    }

}


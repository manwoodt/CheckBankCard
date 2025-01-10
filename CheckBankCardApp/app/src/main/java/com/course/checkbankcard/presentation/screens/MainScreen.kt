package com.course.checkbankcard.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.course.checkbankcard.presentation.viewModels.MainScreenViewModel
import org.koin.androidx.compose.koinViewModel


//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//MainScreen(Modifier,)
//}

@Composable
fun MainScreen(modifier: Modifier, viewModel: MainScreenViewModel= koinViewModel()){
    var inputBinNumber by remember { mutableStateOf("") }

    val binInfo by viewModel.binInfo.observeAsState()
    val errorMessage by viewModel.errorMessage.observeAsState(null)


//    val sampleBinInfo = com.course.domain.model.BinInfo(
//        country = com.course.domain.model.CountryInfo("Россия", 55, 37),
//        scheme = "Visa",
//        bank = com.course.domain.model.BankInfo(
//            "Сбербанк",
//            "www.sberbank.ru",
//            "+7 800 555 55 50",
//            "Москва"
//        )
//    )

    Column(modifier) {
        InputCardNumber(
            inputBinNumber = inputBinNumber,
            onInputChange = { inputBinNumber = it }
        )

        FetchInfoButton {
            viewModel.fetchBinInfo(inputBinNumber)
        }
        binInfo?.let {
            Log.d("mine",it.toString())
            ShowInformationOfCard(it) }
        ErrorDisplay(errorMessage)
    }

}


@Composable
fun InputCardNumber(inputBinNumber:String, onInputChange: (String)-> Unit){

    TextField(
        value = inputBinNumber,
        onValueChange = onInputChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        label = { Text("Enter the first 6 to 8 digits of a card number (BIN/IIN)") }
    )

}

@Composable
fun FetchInfoButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text("Получить информацию")
    }
}

@Composable
fun ShowInformationOfCard(binInfo: com.course.domain.model.BinInfo){
    Column {
        Text(text = "Страна: ${binInfo.country.name}")
        Text(text = "Координаты: ${binInfo.country.latitude},  ${binInfo.country.longitude}")
        Text(text = "Тип карты: ${binInfo.scheme}")
        Text(text = "Банк: ${binInfo.bank.name}")
        Text(text = "Город: ${binInfo.bank.city}")
        Text(text = "Телефон: ${binInfo.bank.phone}")
        Text(text = "Сайт: ${binInfo.bank.url}")
    }
}


@Composable
fun ErrorDisplay(error: String?) {
   if(error != null) Text(text = "Ошибка: $error")
    println(error)
}
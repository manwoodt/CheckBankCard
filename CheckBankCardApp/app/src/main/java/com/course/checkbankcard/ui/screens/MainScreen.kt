package com.course.checkbankcard.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.course.checkbankcard.model.BankInfo
import com.course.checkbankcard.model.BinInfo
import com.course.checkbankcard.model.CountryInfo

@Preview(showBackground = true)
@Composable
fun MainScreen(){
    val sampleBinInfo = BinInfo(
        country = CountryInfo("Россия", 55, 37),
        scheme = "Visa",
        bank = BankInfo("Сбербанк", "www.sberbank.ru", "+7 800 555 55 50", "Москва")
    )
    Column {
        InputCardNumber()
        FetchInfoButton {  }
        ShowInformationOfCard(sampleBinInfo)
        ErrorDisplay("")
    }

}


@Composable
fun InputCardNumber(){

    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = {text = it},
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        label = { Text("Input number of card") }
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
fun ShowInformationOfCard(binInfo: BinInfo){
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
fun ErrorDisplay(error: String) {
    Text(text = "Ошибка: $error")
}
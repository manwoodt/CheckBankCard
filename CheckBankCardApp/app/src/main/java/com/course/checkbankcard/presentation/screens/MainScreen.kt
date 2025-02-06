package com.course.checkbankcard.presentation.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.course.checkbankcard.R
import com.course.checkbankcard.presentation.viewModels.HistoryScreenViewModel
import com.course.checkbankcard.presentation.viewModels.MainScreenViewModel
import com.course.domain.model.BinHistoryItem
import com.course.domain.model.BinInfo
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = koinViewModel(),
) {

    var inputBinNumber by remember { mutableStateOf("") }

    val binInfo by viewModel.binInfo.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState(null)


    Column(modifier = Modifier.padding(16.dp)) {
        InputCardNumber(
            inputBinNumber = inputBinNumber,
            onInputChange = { inputBinNumber = it }
        )
        WarningText()

        FetchInfoButton {
            viewModel.fetchBinInfo(inputBinNumber)
        }
        binInfo?.let {
            ShowInformationOfCard(it)
        }
        ErrorDisplay(errorMessage)

        ButtonToHistoryScreen(navController)
    }

}


@Composable
fun InputCardNumber(inputBinNumber: String, onInputChange: (String) -> Unit) {

    TextField(
        value = inputBinNumber,
        onValueChange = {
            onInputChange(it.replace(" ", ""))
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        label = { Text(stringResource(R.string.input_description)) }
    )

}

@Composable
fun WarningText() {
    Text(
        text = stringResource(R.string.warning),
        color = colorResource(R.color.warning_text_color),
        modifier = Modifier.padding(16.dp),
        style = TextStyle(fontSize = 14.sp)
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
        Text(stringResource(R.string.get_info_about_card))
    }
}

@Composable
fun ShowInformationOfCard(binInfo: BinInfo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {

        LabeledText(R.string.country_label, binInfo.country.name)
        LabeledText(R.string.coordinates_label,
            binInfo.country.latitude + ", " + binInfo.country.longitude)
        LabeledText(R.string.scheme_label, binInfo.scheme)
        LabeledText(R.string.bank_label, binInfo.bank.name)
        LabeledText(R.string.city_label, binInfo.bank.city)
        LabeledText(R.string.phone_label, binInfo.bank.phone)
        LabeledText(R.string.url_label, binInfo.bank.url)

    }
}

@Composable
fun LabeledText(@StringRes labelRes: Int, value: String) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)) {
                append(stringResource(id = labelRes) + ": ")
            }
            append(value)
        }
    )
}


@Composable
fun ErrorDisplay(error: String?) {
    if (error != null)
        Text(text = stringResource(id = R.string.error_message, error), color = Color.Red)
}

@Composable
fun ButtonToHistoryScreen(navController: NavController) {
    Button(
        onClick = { navController.navigate("history_screen") },
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
    ) {
        Text(stringResource(R.string.move_to_history))
    }
}
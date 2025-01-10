package com.course.checkbankcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.course.checkbankcard.presentation.screens.AppNavigation
import com.course.checkbankcard.presentation.ui.theme.CheckBankCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CheckBankCardTheme {
                AppNavigation()
            }
        }
    }
}



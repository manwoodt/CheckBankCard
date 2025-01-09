package com.course.checkbankcard.model

import com.course.checkbankcard.ui.screens.BankInfo
import com.course.checkbankcard.ui.screens.CountryInfo

data class BinInfo(
    val country : CountryInfo,
    val scheme: String,
    val bank: BankInfo
)
package com.course.domain.model

data class BinInfo(
    val country : CountryInfo,
    val scheme: String,
    val bank: BankInfo
)
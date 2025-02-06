package com.course.data.model

data class BinInfoDto(
    val country : CountryInfoDto,
    val scheme: String?,
    val bank: BankInfoDto
)

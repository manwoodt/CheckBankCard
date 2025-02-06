package com.course.data.mappers

import com.course.data.model.BinInfoDto
import com.course.domain.model.BankInfo
import com.course.domain.model.BinInfo
import com.course.domain.model.CountryInfo

fun BinInfoDto.toDomain(): BinInfo {
    return BinInfo(
        country = country.let {
            CountryInfo(
                it.name ?: "Неизвестная страна",
                it.latitude.toString(),
                it.longitude.toString()
            )
        },
        scheme = scheme ?: "Неизвестная система",
        bank = bank.let {
            BankInfo(
                it.name ?: "Неизвестный банк",
                it.url ?: "Неизвестный адрес",
                it.phone ?: "Неизвестный телефон",
                it.city ?: "Неизвестный город"
            )
        }
    )
}
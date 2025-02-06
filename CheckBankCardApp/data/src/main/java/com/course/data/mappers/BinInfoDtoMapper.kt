package com.course.data.mappers

import com.course.data.model.BinInfoDto
import com.course.domain.model.BankInfo
import com.course.domain.model.BinInfo
import com.course.domain.model.CountryInfo

fun BinInfoDto.toDomain(): BinInfo {
    return BinInfo(
        country = country.let {
            CountryInfo(
                it.name ?: "Unknown name",
                it.latitude.toString(),
                it.longitude.toString()
            )
        },
        scheme = scheme ?: "Unknown scheme",
        bank = bank.let {
            BankInfo(
                it.name ?: "Unknown name",
                it.url ?: "Unknown url",
                it.phone ?: "Unknown phone",
                it.city ?: "Unknown city"
            )
        }
    )
}
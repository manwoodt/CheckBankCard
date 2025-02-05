package com.course.data.mappers

import com.course.data.room.BinInfoEntity
import com.course.domain.model.BankInfo
import com.course.domain.model.BinInfo
import com.course.domain.model.CountryInfo

fun BinInfo.toEntity(bin:String):BinInfoEntity{
    return BinInfoEntity(
        bin = bin,
        country = country?.let {
            CountryInfo(it.name,it.latitude,it.longitude)
        },
        scheme = scheme,
        bank = bank?.let {
            BankInfo(it.name,it.url, it.phone, it.city)
        }
    )
}

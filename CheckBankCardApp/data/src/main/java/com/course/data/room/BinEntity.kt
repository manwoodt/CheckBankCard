package com.course.data.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.course.domain.model.BankInfo
import com.course.domain.model.CountryInfo


@Entity(tableName = "bin_info")
data class BinInfoEntity(
    @PrimaryKey(autoGenerate = true) val uid:Int = 0,
    val bin: String,
    @Embedded(prefix = "country_")val country : CountryInfo,
    val scheme : String,
    @Embedded(prefix = "bank_")val bank : BankInfo


)

package com.course.domain.repository

import com.course.domain.model.BinInfo

interface BinRepository {
    suspend fun getBinInfo(bin:String): BinInfo
}
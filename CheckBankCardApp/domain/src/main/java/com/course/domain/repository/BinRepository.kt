package com.course.domain.repository

import com.course.domain.model.BinHistoryItem
import com.course.domain.model.BinInfo
import kotlinx.coroutines.flow.Flow

interface BinRepository {
    suspend fun getBinInfo(bin:String): BinInfo
    suspend fun getBinInfoHistory(): Flow<List<BinHistoryItem>>
    suspend fun clearHistory()
}
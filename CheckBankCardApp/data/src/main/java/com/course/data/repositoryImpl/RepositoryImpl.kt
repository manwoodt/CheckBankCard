package com.course.data.repositoryImpl

import com.course.data.api.BinApiService
import com.course.data.mappers.toDomain
import com.course.data.mappers.toEntity
import com.course.data.room.BinInfoDao
import com.course.domain.model.BinHistoryItem
import com.course.domain.model.BinInfo
import com.course.domain.repository.BinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BinRepositoryImpl(
    private val apiService: BinApiService,
    private val dao: BinInfoDao,
) : BinRepository {

    override suspend fun getBinInfo(bin: String): BinInfo {
        val response = apiService.getBinInfo(bin).toDomain()
        dao.insertBinInfo(response.toEntity(bin))
        return response
    }

    override suspend fun getBinInfoHistory()
            : Flow<List<BinHistoryItem>> {
        return dao.getAllBinInfo().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun clearHistory() {
       dao.deleteAll()
    }
}


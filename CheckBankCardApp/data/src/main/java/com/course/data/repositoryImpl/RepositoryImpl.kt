package com.course.data.repositoryImpl

import com.course.data.api.BinApiService
import com.course.domain.model.BinInfo
import com.course.domain.repository.BinRepository

class BinRepositoryImpl(private val apiService: BinApiService) : BinRepository {

    override suspend fun getBinInfo(binNumber: String): BinInfo {
        return apiService.getBinInfo(binNumber)
    }

}
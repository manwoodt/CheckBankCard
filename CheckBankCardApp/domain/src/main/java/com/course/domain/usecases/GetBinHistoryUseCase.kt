package com.course.domain.usecases

import com.course.domain.model.BinHistoryItem
import com.course.domain.repository.BinRepository
import kotlinx.coroutines.flow.Flow

class GetBinHistoryUseCase(private val repository: BinRepository) {
    suspend operator fun invoke(): Flow<List<BinHistoryItem>> {
        return repository.getBinInfoHistory()
    }
}
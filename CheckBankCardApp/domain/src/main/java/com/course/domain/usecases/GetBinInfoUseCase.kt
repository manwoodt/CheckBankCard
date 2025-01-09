package com.course.domain.usecases

import com.course.domain.model.BinInfo
import com.course.domain.repository.BinRepository

class GetBinInfoUseCase(repository: BinRepository) {
    suspend operator fun invoke(): BinInfo {
        return repository.getBinInfo()
    }
}
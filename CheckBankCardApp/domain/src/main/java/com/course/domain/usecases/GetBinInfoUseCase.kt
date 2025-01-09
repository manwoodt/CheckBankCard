package com.course.domain.usecases

import com.course.domain.model.BinInfo
import com.course.domain.repository.BinRepository

class GetBinInfoUseCase(private val repository: BinRepository) {
    suspend operator fun invoke(bin:String): BinInfo {
        return repository.getBinInfo(bin)
    }
}
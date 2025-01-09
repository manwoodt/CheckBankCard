package com.course.checkbankcard.di

import com.course.data.BinRepositoryImpl
import com.course.domain.repository.BinRepository
import com.course.domain.usecases.GetBinInfoUseCase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<BinRepository> { BinRepositoryImpl(get()) }

    factory { GetBinInfoUseCase(get()) }

    viewModel { MainScreenViewModel(get()) }
}
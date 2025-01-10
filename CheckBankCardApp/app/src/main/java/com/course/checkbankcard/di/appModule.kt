package com.course.checkbankcard.di

import com.course.checkbankcard.presentation.viewModels.MainScreenViewModel
import com.course.data.api.BinApiService
import com.course.data.repositoryImpl.BinRepositoryImpl
import com.course.domain.repository.BinRepository
import com.course.domain.usecases.GetBinInfoUseCase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinApiService::class.java)
    }

    single<BinRepository> { BinRepositoryImpl(get()) }
    factory { GetBinInfoUseCase(get()) }

    viewModel { MainScreenViewModel(get()) }
}
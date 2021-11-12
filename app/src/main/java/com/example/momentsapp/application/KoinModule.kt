package com.example.momentsapp.application

import com.example.momentsapp.data.repository.MomentRepositoryImplementation
import com.example.momentsapp.domain.repository.MomentRepository
import com.example.momentsapp.domain.usecase.CreateMoment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {
    single { MomentRepositoryImplementation() as MomentRepository }

    single { CreateMoment(get()) }

    // viewModel {  }
}
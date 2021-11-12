package com.example.momentsapp.application

import com.example.momentsapp.data.repository.*
import com.example.momentsapp.domain.repository.*
import com.example.momentsapp.domain.usecase.*
import com.example.momentsapp.presentation.new_moment.CreateMomentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {
    single { MomentRepositoryImplementation() as MomentRepository }

    single { CreateMoment(get()) }

    viewModel { CreateMomentViewModel(get()) }
}
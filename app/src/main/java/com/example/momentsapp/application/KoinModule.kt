package com.example.momentsapp.application

import com.example.momentsapp.data.firebase.FirebaseStorageManager
import com.example.momentsapp.data.firebase.FirestoreManager
import com.example.momentsapp.data.repository.*
import com.example.momentsapp.domain.repository.*
import com.example.momentsapp.domain.usecase.*
import com.example.momentsapp.presentation.home.viewModel.HomeViewModel
import com.example.momentsapp.presentation.new_moment.CreateMomentViewModel
import com.google.firebase.database.FirebaseDatabase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {

    single { FirebaseDatabase.getInstance() }

    single { FirestoreManager() }

    single { FirebaseStorageManager() }

    single { FileRepositoryImplementation(get()) as FileRepository }

    single { MomentRepositoryImplementation(get(), get()) as MomentRepository }

    single { CreateMoment(get()) }

    single { GetMoments(get()) }

    viewModel { CreateMomentViewModel(get()) }

    viewModel { HomeViewModel(get()) }
}
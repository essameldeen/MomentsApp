package com.example.momentsapp.presentation.new_moment

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.momentsapp.domain.usecase.CreateMoment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateNewMomentViewModel constructor(private val createMoment: CreateMoment) : ViewModel()
{
    var state = MutableLiveData<CreateNewMomentViewState>()

    var photoPath = ""

    fun createMoment(title: String, description: String, country: String, city: String) = CoroutineScope(Dispatchers.IO).launch {
        state.postValue(CreateNewMomentViewState.LoadingCreationState)
        createMoment.run(title, description, photoPath, country, city, {
            state.postValue(CreateNewMomentViewState.SuccessfulCreationState)
        }, {
            state.postValue(CreateNewMomentViewState.ErrorCreationState(it))
        })
    }

    fun clear()
    {
        state = MutableLiveData<CreateNewMomentViewState>()
    }
}
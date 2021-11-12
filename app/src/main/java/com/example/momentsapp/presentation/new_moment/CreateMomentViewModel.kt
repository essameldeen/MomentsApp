package com.example.momentsapp.presentation.new_moment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.momentsapp.domain.usecase.CreateMoment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateMomentViewModel constructor(private val createMoment: CreateMoment): ViewModel()
{
    val result = MutableLiveData<Boolean>()

    fun createMoment() = CoroutineScope(Dispatchers.IO).launch {
        createMoment.run()
        result.postValue(true)
    }
}
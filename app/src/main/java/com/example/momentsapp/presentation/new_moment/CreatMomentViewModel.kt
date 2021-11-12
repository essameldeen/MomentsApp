package com.example.momentsapp.presentation.new_moment

import androidx.lifecycle.MutableLiveData
import com.example.momentsapp.domain.usecase.CreateMoment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreatMomentViewModel constructor(private val createMoment: CreateMoment)
{
    val result = MutableLiveData<Boolean>()

    fun creatMoment() = CoroutineScope(Dispatchers.IO).launch {
        createMoment.run()
        result.postValue(true)
    }
}
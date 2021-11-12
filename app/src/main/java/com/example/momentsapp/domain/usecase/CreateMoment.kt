package com.example.momentsapp.domain.usecase

import com.example.momentsapp.data.model.Moment
import com.example.momentsapp.domain.repository.MomentRepository

class CreateMoment constructor(private val repository: MomentRepository)
{
    fun run (): Moment
    {

        repository.createMoment()


       return  Moment()
    }
}
package com.example.momentsapp.domain.usecase

import com.example.momentsapp.domain.repository.MomentRepository



class LikeMoment constructor(private val repository: MomentRepository)
{
    fun run(moment: String, listener: (Boolean) -> Unit) = repository.incrementMomentLikes(moment, listener)
}
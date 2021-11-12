package com.example.momentsapp.domain.usecase

import com.example.momentsapp.domain.repository.MomentRepository

class GetMoments constructor(private val repository: MomentRepository)
{
    fun run(){
        val cached = repository.getCachedMoments()
        if (cached.isEmpty()){
            // get from remote
        }
    }
}
package com.example.momentsapp.domain.usecase

import com.example.momentsapp.data.model.Moment
import com.example.momentsapp.domain.repository.MomentRepository

class GetMoments constructor(private val repository: MomentRepository) {
    fun run(): MutableList<Moment> {
        val cached = repository.getCachedMoments()
        if (cached.isEmpty()) {
            return repository.getMoments()
        }
        return cached
    }
}
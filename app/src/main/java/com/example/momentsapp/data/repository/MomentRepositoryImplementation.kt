package com.example.momentsapp.data.repository

import com.example.momentsapp.data.model.Moment
import com.example.momentsapp.domain.repository.MomentRepository

class MomentRepositoryImplementation constructor(): MomentRepository
{
    private var moments = mutableListOf<Moment>()

    override fun getCachedMoments(): MutableList<Moment> = moments

    override fun createMoment()
    {
        TODO("Not yet implemented")
    }
}
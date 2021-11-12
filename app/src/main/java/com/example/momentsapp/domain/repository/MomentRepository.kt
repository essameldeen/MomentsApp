package com.example.momentsapp.domain.repository

import com.example.momentsapp.data.model.Moment

interface MomentRepository
{
    fun getCachedMoments(): MutableList<Moment>

    fun createMoment(moment: Moment, listener: (Boolean) -> Unit)

    fun getMoments(listener: (MutableList<Moment>) -> Unit)
}
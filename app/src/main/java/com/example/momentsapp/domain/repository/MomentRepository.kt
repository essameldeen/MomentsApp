package com.example.momentsapp.domain.repository

import com.example.momentsapp.data.model.Moment

interface MomentRepository {
    fun createMoment(moment: Moment, listener: (Boolean) -> Unit)
    fun getCachedMoments(): MutableList<Moment>
    fun getMoments(listener: (MutableList<Moment>) -> Unit)

}
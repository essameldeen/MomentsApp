package com.example.momentsapp.domain.repository

import com.example.momentsapp.data.model.Moment

interface MomentRepository {
    fun getCachedMoments(): MutableList<Moment>
    fun getMoments(): MutableList<Moment>
    fun createMoment()
}
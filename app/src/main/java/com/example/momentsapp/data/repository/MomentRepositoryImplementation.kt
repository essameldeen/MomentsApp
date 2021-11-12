package com.example.momentsapp.data.repository

import com.example.momentsapp.data.model.Moment
import com.example.momentsapp.domain.repository.MomentRepository

class MomentRepositoryImplementation constructor(private val database: FirestoreManager): MomentRepository
{
    override fun createMoment(moment: Moment, listener: (Boolean) -> Unit)
    {
    private var moments = mutableListOf<Moment>()
    override fun getCachedMoments(): MutableList<Moment> = moments
    override fun getMoments(): MutableList<Moment> {
            // call fireBase

         // set cached
        return  moments
    }

    override fun createMoment() {}
}
package com.example.momentsapp.data.repository

import com.example.momentsapp.data.firebase.FirestoreManager
import com.example.momentsapp.data.model.Moment
import com.example.momentsapp.domain.repository.MomentRepository

class MomentRepositoryImplementation constructor(private val database: FirestoreManager): MomentRepository
{
    override fun createMoment(moment: Moment, listener: (Boolean) -> Unit)
    {

    }
}
package com.example.momentsapp.data.repository

import com.example.momentsapp.data.firebase.FirestoreManager
import com.example.momentsapp.data.model.Moment
import com.example.momentsapp.domain.repository.MomentRepository
import com.google.firebase.firestore.FieldValue

class MomentRepositoryImplementation constructor(private val database: FirestoreManager) :
    MomentRepository {
    private var moments = mutableListOf<Moment>()

    override fun getCachedMoments(): MutableList<Moment> = moments

    override fun createMoment(moment: Moment, listener: (Boolean) -> Unit) =
        database.addMoment(moment, listener)

    override fun getMoments(listener: (MutableList<Moment>) -> Unit) {
        database.listenToMoments {
            moments = it
            listener(moments)
        }
    }

    override fun incrementMomentLikes(moment: String, listener: (Boolean) -> Unit) {
        database.incrementMomentLikes(moment, listener)
    }


}
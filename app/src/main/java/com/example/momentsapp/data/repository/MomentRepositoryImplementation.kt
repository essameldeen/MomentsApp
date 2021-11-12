package com.example.momentsapp.data.repository

import com.example.momentsapp.data.firebase.FirestoreManager
import com.example.momentsapp.data.model.Moment
import com.example.momentsapp.domain.repository.MomentRepository
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue

class MomentRepositoryImplementation constructor(
    private val database: FirestoreManager
) : MomentRepository {

    override fun createMoment(moment: Moment, listener: (Boolean) -> Unit) {}

    private var moments = mutableListOf<Moment>()

    override fun getCachedMoments(): MutableList<Moment> = moments

    override fun getMoments(listener: (MutableList<Moment>) -> Unit){
        database.listenToMoments {
            moments = it
            listener(moments)
        }
    }
}
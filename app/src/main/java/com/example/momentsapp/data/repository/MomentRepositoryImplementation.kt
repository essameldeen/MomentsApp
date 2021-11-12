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
    private val database: FirestoreManager, private val firebaseDatabase: FirebaseDatabase
) : MomentRepository {
    override fun createMoment(moment: Moment, listener: (Boolean) -> Unit) {}
    private var moments = mutableListOf<Moment>()
    override fun getCachedMoments(): MutableList<Moment> = moments
    override fun getMoments(): MutableList<Moment> {
        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val moment = snapshot.getValue<Moment>()
                moment?.let {
                    moments.add(moment)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        firebaseDatabase.reference.addChildEventListener(childEventListener)
        return moments
    }
}
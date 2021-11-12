package com.example.momentsapp.data.firebase

import com.example.momentsapp.data.model.Moment
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreManager
{
    fun addMoment(moment: Moment, listener: (Boolean) -> Unit)
    {
        val db = FirebaseFirestore.getInstance()
        db.collection("moments").document(moment.id).set(mapOf(
            "id" to moment.id,
            "title" to moment.title,
            "description" to moment.description,
            "photo" to moment.photo,
            "country" to moment.country,
            "city" to moment.city,
            "likes" to moment.likes,
        )).addOnSuccessListener {
            listener(true)
        }.addOnFailureListener {
            it.printStackTrace()
            listener(false)
        }
    }
}
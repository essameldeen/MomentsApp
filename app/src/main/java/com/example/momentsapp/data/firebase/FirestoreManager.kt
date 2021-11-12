package com.example.momentsapp.data.firebase

import com.example.momentsapp.data.model.Moment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreManager {
    fun addMoment(moment: Moment, listener: (Boolean) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("moments").document(moment.id).set(
            mapOf(
                "id" to moment.id,
                "title" to moment.title,
                "description" to moment.description,
                "photo" to moment.photo,
                "country" to moment.country,
                "city" to moment.city,
                "likes" to moment.likes,
            )
        ).addOnSuccessListener {
            listener(true)
        }.addOnFailureListener {
            it.printStackTrace()
            listener(false)
        }
    }

    fun listenToMoments(listener: (MutableList<Moment>) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        try {
            val reference = db.collection("moments")
            reference.addSnapshotListener { snapshot, e ->
                if (e != null) {
                    e.printStackTrace()
                } else if (snapshot != null && !snapshot.isEmpty) {
                    val moments = mutableListOf<Moment>()
                    for (document in snapshot.documents) {
                        val data = document.data
                        if (data != null) {
                            for (momentItem in data.values) {
                                val allMoments = momentItem as ArrayList<HashMap<String, Any?>>

                                for (item in allMoments) {
                                    val id = item["id"].toString()
                                    val name = item["name"].toString()
                                    val description = item["description"].toString()
                                    val photo = item["photo"].toString()
                                    val country = item["country"].toString()
                                    val city = item["city"].toString()
                                    val likes = item["likes"].toString().toLong()

                                    moments.add(
                                        Moment(
                                            id,
                                            name,
                                            description,
                                            photo,
                                            country,
                                            city,
                                            likes
                                        )
                                    )
                                }
                            }
                        }
                    }
                    listener(moments)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
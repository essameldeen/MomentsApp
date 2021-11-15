package com.example.momentsapp.data.firebase

import com.example.momentsapp.data.model.Moment
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreManager
{
    val db = FirebaseFirestore.getInstance()
    fun addMoment(moment: Moment, listener: (Boolean) -> Unit)
    {
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

    fun listenToMoments(listener: (MutableList<Moment>) -> Unit)
    {

        try
        {
            val reference = db.collection("moments")
            reference.addSnapshotListener { snapshot, e ->
                if (e != null) e.printStackTrace()
                else if (snapshot != null && !snapshot.isEmpty)
                {
                    val moments = mutableListOf<Moment>()
                    for (document in snapshot.documents)
                    {
                        val data = document.data
                        if (data != null)
                        {
                            val id = data["id"].toString()
                            val title = data["title"].toString()
                            val description = data["description"].toString()
                            val photo = data["photo"].toString()
                            val country = data["country"].toString()
                            val city = data["city"].toString()
                            val likes = data["likes"].toString().toLong()
                            moments.add(Moment(id, title, description, photo, country, city, likes))
                        }
                    }
                    listener(moments)
                }
            }
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
    }
    fun incrementMomentLikes(moment: String, listener: (Boolean) -> Unit){
        db.collection("moments").document(moment).update("likes", FieldValue.increment(1)).addOnSuccessListener {
            listener(true)
        }.addOnFailureListener {
            it.printStackTrace()
            listener(false)
        }
    }
}
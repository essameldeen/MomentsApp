package com.example.momentsapp.data.firebase

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage

class FirebaseStorageManager
{
    private val storage = FirebaseStorage.getInstance()

    fun upload(moment: String, file: Uri, listener: (String) -> Unit, onError: (Throwable) -> Unit)
    {
        // Create a storage reference from our app
        val storageRef = storage.reference
        val reference = storageRef.child("moments/$moment.png")
        val uploadTask = reference.putFile(file)

        uploadTask.addOnSuccessListener {
            reference.downloadUrl.addOnSuccessListener { uri ->
                listener(uri.toString())
            }.addOnFailureListener {
                it.printStackTrace()
                onError(it)
            }
        }.addOnFailureListener {
            it.printStackTrace()
            onError(it)
        }
    }
}
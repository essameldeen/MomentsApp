package com.example.momentsapp.data.repository

import android.net.Uri
import com.example.momentsapp.data.firebase.FirebaseStorageManager
import com.example.momentsapp.domain.repository.FileRepository

class FileRepositoryImplementation constructor(private val storage: FirebaseStorageManager): FileRepository
{
    override fun upload(moment: String, file: Uri, listener: (String) -> Unit, onError: (Throwable) -> Unit) = storage.upload(moment, file, listener, onError)
}
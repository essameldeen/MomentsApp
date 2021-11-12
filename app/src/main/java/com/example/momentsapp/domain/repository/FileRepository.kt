package com.example.momentsapp.domain.repository

import android.net.Uri

interface FileRepository
{
    fun upload(moment: String, file: Uri, listener: (String) -> Unit, onError: (Throwable) -> Unit)
}
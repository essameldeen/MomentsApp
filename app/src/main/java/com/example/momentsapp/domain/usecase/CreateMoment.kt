package com.example.momentsapp.domain.usecase

import android.net.Uri
import com.example.momentsapp.data.model.Moment
import com.example.momentsapp.domain.repository.FileRepository
import com.example.momentsapp.domain.repository.MomentRepository
import java.util.*

class CreateMoment constructor(private val momentRepository: MomentRepository, private val fileRepository: FileRepository)
{
    fun run(title: String, description: String, photo: Uri, country: String, city: String, onSuccess: () -> Unit, onError: (Throwable) -> Unit)
    {
        val id = Date().time.toString()
        fileRepository.upload(id, photo, {
            val moment = Moment(id, title, description, it, country, city)
            momentRepository.createMoment(moment) { success ->
                if (success) onSuccess()
                else onError(Throwable("Something went wrong! … Please try again later."))
            }
        }, {
            onError(it)
        })
    }
}
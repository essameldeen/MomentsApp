package com.example.momentsapp.domain.usecase

import android.net.Uri
import com.example.momentsapp.data.model.Moment
import com.example.momentsapp.domain.repository.FileRepository
import com.example.momentsapp.domain.repository.MomentRepository
import java.util.*

class CreateMoment constructor(private val momentRepository: MomentRepository, private val fileRepository: FileRepository)
{
    fun run(title: String, description: String, photo: String, country: String, city: String, onSuccess: () -> Unit, onError: (Throwable) -> Unit)
    {
        when {
            title.isEmpty() -> onError(Throwable("Title is missing!"))
            description.isEmpty() -> onError(Throwable("Description is missing!"))
            photo.isEmpty() -> onError(Throwable("Photo is missing!"))
            country.isEmpty() -> onError(Throwable("Country is missing!"))
            city.isEmpty() -> onError(Throwable("City is missing!"))
            else -> {
                val id = Date().time.toString()
                fileRepository.upload(id, Uri.parse("file://$photo"), {
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
    }
}
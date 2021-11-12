package com.example.momentsapp.data.model

data class Moment(
    val id: Int?,
    val title: String?,
    val description: String?,
    val image: String?,
    val country: String?,
    val city: String?,
    var like: Boolean? = false
)

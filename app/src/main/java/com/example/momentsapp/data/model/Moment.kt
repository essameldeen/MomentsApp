package com.example.momentsapp.data.model

data class Moment(
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var photo: String = "",
    var country: String = "",
    var city: String = "",
    var likes: Long = 0,
)
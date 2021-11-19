package com.example.recyclerviewproject.response

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val email: String,
    val gender: String,
    val id: Int,
    var name: String,
    val status: String
)
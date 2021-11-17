package com.example.recyclerviewproject.response

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val email: String,
    val gender: String,
    val id: Int,
    val name: String,
    val status: String
)
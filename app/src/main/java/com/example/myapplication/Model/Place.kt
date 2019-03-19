package com.example.myapplication.Model

import java.io.Serializable

data class Place(
    val name: String,
    val city: String,
    val latitude: Double?,
    val longitude: Double?,
    val description: String?
) : Serializable
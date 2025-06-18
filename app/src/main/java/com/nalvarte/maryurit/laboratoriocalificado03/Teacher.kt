package com.nalvarte.maryurit.laboratoriocalificado03

import com.google.gson.annotations.SerializedName

data class Teacher(
    val name: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("phone_number") val phoneNumber: String,
    val email: String,
    @SerializedName("image_url") val imageUrl: String
)
package com.nalvarte.maryurit.laboratoriocalificado03

import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("list/teacher-b")
    suspend fun getTeachers(): List<Teacher>
}
package dev.chepkoech.workoutlog.api

import dev.chepkoech.workoutlog.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response <RegisterResponse>

    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response <LoginResponse>
    @GET("/exercise-categories")
    suspend fun  fetchExerciseCategory(@Header("Authorization")token: String):Response<List<ExerciseCategory>>
}
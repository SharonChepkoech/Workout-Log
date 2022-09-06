package dev.chepkoech.workoutlog

import dev.chepkoech.workoutlog.models.LoginRequest
import dev.chepkoech.workoutlog.models.LoginResponse
import dev.chepkoech.workoutlog.models.RegisterRequest
import dev.chepkoech.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call <RegisterResponse>

    @POST("/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call <LoginResponse>
}
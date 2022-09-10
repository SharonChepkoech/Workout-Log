package dev.chepkoech.workoutlog.repository

import dev.chepkoech.workoutlog.api.ApiClient
import dev.chepkoech.workoutlog.api.ApiInterface
import dev.chepkoech.workoutlog.models.LoginRequest
import dev.chepkoech.workoutlog.models.LoginResponse
import dev.chepkoech.workoutlog.models.RegisterRequest
import dev.chepkoech.workoutlog.models.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>
    = withContext(Dispatchers.IO){
        val response = apiClient.loginUser(loginRequest)
        return@withContext response
    }
    suspend fun registerUser(registerRequest: RegisterRequest):Response<RegisterResponse>
    = withContext((Dispatchers.IO)){
        val response = apiClient.registerUser(registerRequest)
        return@withContext response
    }
}
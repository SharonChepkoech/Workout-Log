package dev.chepkoech.workoutlog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.chepkoech.workoutlog.models.LoginRequest
import dev.chepkoech.workoutlog.models.LoginResponse
import dev.chepkoech.workoutlog.models.RegisterRequest
import dev.chepkoech.workoutlog.models.RegisterResponse
import dev.chepkoech.workoutlog.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    val userRepository = UserRepository()
    val loginLiveData = MutableLiveData<LoginResponse>()
    val loginError = MutableLiveData<String>()
    val registerLiveData = MutableLiveData<RegisterResponse>()
    val registerError = MutableLiveData<String>()

    fun login(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response = userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                loginLiveData.postValue(response.body())
            }
            else{
                loginError.postValue((response.errorBody()?.string()))
            }
        }
    }
    fun register(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response = userRepository.registerUser(registerRequest)
            if(response.isSuccessful){
                registerLiveData.postValue(response.body())
            }
            else{
                registerError.postValue(response.errorBody()?.string())
            }
        }
    }
}
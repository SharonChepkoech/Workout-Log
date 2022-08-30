package dev.chepkoech.workoutlog.models

//This is the outer layout of response
data class RegisterResponse(
    var message: String,
    var user: User
)

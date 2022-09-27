package dev.chepkoech.workoutlog.repository

import dev.chepkoech.workoutlog.api.ApiClient
import dev.chepkoech.workoutlog.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepository {
    val apiClient = ApiClient.buildApiClient((ApiInterface::class.java))

    suspend fun fetchExerciseCategories(accessToken: String)
    = withContext(Dispatchers.IO){
        return@withContext apiClient.fetchExerciseCategory(accessToken)

    }
}
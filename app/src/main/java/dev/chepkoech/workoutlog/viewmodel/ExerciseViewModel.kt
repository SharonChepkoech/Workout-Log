package dev.chepkoech.workoutlog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.chepkoech.workoutlog.models.ExerciseCategory
import dev.chepkoech.workoutlog.repository.ExerciseRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ExerciseViewModel:ViewModel() {
    val exerciseRepository = ExerciseRepository()
    val exerciseCategoryLiveData =MutableLiveData<List<ExerciseCategory>>()
    val errorLiveData = MutableLiveData<String>()

    fun fetchExerciseCategories(accessToken:String){
        viewModelScope.launch {
            val response = exerciseRepository.fetchExerciseCategories(accessToken)
            if(response.isSuccessful){
                exerciseCategoryLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}

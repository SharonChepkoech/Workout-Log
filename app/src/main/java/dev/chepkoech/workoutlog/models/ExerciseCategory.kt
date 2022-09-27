package dev.chepkoech.workoutlog.models

import com.google.gson.annotations.SerializedName

data class ExerciseCategory(
    @SerializedName("category_name") var categoryName: String,
    @SerializedName("category_id") var categoryId: String
)

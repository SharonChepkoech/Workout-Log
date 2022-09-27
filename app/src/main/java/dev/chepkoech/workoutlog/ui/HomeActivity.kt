package dev.chepkoech.workoutlog.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import dev.chepkoech.workoutlog.ProfileFragment
import dev.chepkoech.workoutlog.R
import dev.chepkoech.workoutlog.databinding.ActivityHomeBinding
import dev.chepkoech.workoutlog.viewmodel.ExerciseViewModel
import dev.chepkoech.workoutlog.viewmodel.UserViewModel
import kotlinx.coroutines.internal.ThreadSafeHeap

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    val exerciseViewModel: ExerciseViewModel by viewModels()
    lateinit var  sharedPrefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNav()
        triggerFetchExerciseCategories()
    }


    override fun onResume() {
        super.onResume()
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer {categoryResponse->
            Toast.makeText(this,"fetched ${categoryResponse.size} category", Toast.LENGTH_LONG)
        })
        exerciseViewModel.errorLiveData.observe(this, Observer { errorMessage->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        })
    }

    fun triggerFetchExerciseCategories(){
        sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
        val accessToken = sharedPrefs.getString("ACCESS_TOKEN", "")
                exerciseViewModel.fetchExerciseCategories(accessToken!!)

    }

    fun setupBottomNav(){
        binding.bottomNavigation.setOnItemSelectedListener {item->
            when(item.itemId){
                R.id.plan ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, PlanFragment()).commit()
                    true
                }
                R.id.track ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, TrackFragment()).commit()
                    true
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fcvHome,
                        ProfileFragment()
                    ).commit()
                    true
                }
                else-> false
            }

        }

    }
}
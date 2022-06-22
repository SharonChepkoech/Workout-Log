package dev.chepkoech.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.chepkoech.workoutlog.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNav()
    }

    fun setupBottomNav(){
        binding.bottomNavigation.setOnItemSelectedListener {item->
            when(item.itemId){
                R.id.plan->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome,PlanFragment()).commit()
                    true
                }
                R.id.track->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome,TrackFragment()).commit()
                    true
                }
                R.id.profile->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome,ProfileFragment()).commit()
                    true
                }
                else-> false
            }

        }

    }
}
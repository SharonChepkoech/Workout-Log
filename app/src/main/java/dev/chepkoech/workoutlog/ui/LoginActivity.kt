package dev.chepkoech.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.chepkoech.workoutlog.api.ApiClient
import dev.chepkoech.workoutlog.api.ApiInterface
import dev.chepkoech.workoutlog.databinding.ActivityLoginBinding
import dev.chepkoech.workoutlog.models.LoginRequest
import dev.chepkoech.workoutlog.models.LoginResponse
import dev.chepkoech.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel : UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)

        setContentView(binding.root)

        binding.tvSignUp.setOnClickListener {
            intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            validate()
//            val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginLiveData.observe(this, Observer { loginResponse ->
            Toast.makeText(baseContext, loginResponse?.message, Toast.LENGTH_LONG).show()
            persistLoginDetails(loginResponse!!)
            startActivity(Intent(baseContext, HomeActivity::class.java))
        })

        userViewModel.loginError.observe(this, Observer{ errorMessage ->
            Toast.makeText(baseContext, errorMessage, Toast.LENGTH_LONG).show()
        })
    }

    fun validate() {
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var error = false

        if (email.isBlank()) {
            error = true
            binding.tilEmail.error = "Please input email"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            error = true
            binding.tilEmail.error = "Email is invalid"
        }
        if (password.isBlank()) {
            error = true
            binding.tilPassword.error = "Please input password"
        }
        if (!error) {
            val loginRequest = LoginRequest(email, password)
            userViewModel.login(loginRequest)
        }
    }

    fun persistLoginDetails(loginResponse: LoginResponse){
        val editor = sharedPrefs.edit()
        val token = "Bearer ${loginResponse.accessToken}"
        editor.putString("USER_ID", loginResponse.userId)
        editor.putString("ACCESS_TOKEN", token)
        editor.putString("PROFILE_ID", loginResponse.profileId)
        editor.apply()
//        We us the editor to . All keys are in all caps
    }
}
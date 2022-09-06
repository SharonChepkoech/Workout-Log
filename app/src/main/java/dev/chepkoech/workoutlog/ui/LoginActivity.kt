package dev.chepkoech.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import dev.chepkoech.workoutlog.ApiClient
import dev.chepkoech.workoutlog.ApiInterface
import dev.chepkoech.workoutlog.ui.SignupActivity
import dev.chepkoech.workoutlog.databinding.ActivityLoginBinding
import dev.chepkoech.workoutlog.models.LoginRequest
import dev.chepkoech.workoutlog.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
//        sharedPrefs = sharedPreferences(name: "WORKOUTLOG_PREFS", "MODE_PRIVATE")

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
            makeLoginRequest(loginRequest)
        }
    }

    fun makeLoginRequest(loginRequest: LoginRequest) {
        val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.loginUser(loginRequest)

        request.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    Toast.makeText(baseContext, loginResponse?.message, Toast.LENGTH_LONG).show()
                    persistLoginDetails(loginResponse!!)
                    startActivity(Intent(baseContext, HomeActivity::class.java))
                }
                else{
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
    fun persistLoginDetails(loginResponse: LoginResponse){
        val editor = sharedPrefs.edit()
        editor.putString("USER_ID", loginResponse.userId)
        editor.putString("ACCESS_TOKEN", loginResponse.accessToken)
        editor.putString("PROFILE_ID", loginResponse.profileId)
        editor.apply()
//        We us the editor to . All keys are in all caps
    }

}
package dev.chepkoech.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.chepkoech.workoutlog.api.ApiInterface
import dev.chepkoech.workoutlog.databinding.ActivitySignupBinding
import dev.chepkoech.workoutlog.api.ApiClient
import dev.chepkoech.workoutlog.models.RegisterRequest
import dev.chepkoech.workoutlog.models.RegisterResponse
import dev.chepkoech.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)

//        setContentView(binding.root)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLogin.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignUp.setOnClickListener {
            validate()

        }
    }

    override fun onResume(){
        super.onResume()
        userViewModel.registerLiveData.observe(this, Observer { RegisterResponse ->
            Toast.makeText(baseContext, RegisterResponse?.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext, LoginActivity::class.java))

        })
        userViewModel.registerError.observe(this, Observer { errorMessage ->
            Toast.makeText(baseContext, errorMessage, Toast.LENGTH_LONG).show()

        })
    }

        fun validate(){
            var firstName = binding.etFirstName.text.toString()
            var lastName = binding.etLastName.text.toString()
            var email = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()
            var confirmPassword = binding.etConfirmPassword.text.toString()

            var error = false

            if (firstName.isBlank()){
                error = true
                binding.tilFirstName.error = "Please input  First Name"
            }
            if (lastName.isBlank()){
                error = true
                binding.tilLastName.error = "Please input Last Name"
            }
            if (email.isBlank()){
                error = true
                binding.tilEmail.error = "Please  input email"
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                binding.tilEmail.error = "Emails do not match"
            if (password.isBlank()){
                error = true
                binding.tilPassword.error = "Please input password"
            }
            if (confirmPassword.isBlank()){
                error = true
                binding.tilConfirmPassword.error = "Please confirm your password"
            }
//            if (confirmPassword != password){
//                binding.tilConfirmPassword.error = "Please enter the same password"
//            }
            if(!password.equals(confirmPassword)) {
                error = true
                binding.tilConfirmPassword.error = "Please enter the same password"
            }

            if(!error){
                var registerRequest = RegisterRequest(firstName,lastName,password,email,password)
                userViewModel.register(registerRequest)
            }
        }
//    fun makeRegistrationRequest(registerRequest: RegisterRequest){
//        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
//        var request =apiClient.registerUser(registerRequest )
//
//        request.enqueue(object : Callback<RegisterResponse>{
//            override fun onResponse(
//                call: Call<RegisterResponse>,
//                response: Response<RegisterResponse>) {
//                if(response.isSuccessful){
//                    var message = response.body()?.message
//                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
//                    startActivity(Intent(baseContext, LoginActivity::class.java))
//                }
//                else {
//                    var error = response.errorBody()?.string()
//                    Toast.makeText(baseContext,error, Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
//            }
//
//        })
//    }
}




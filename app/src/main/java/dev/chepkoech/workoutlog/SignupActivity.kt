package dev.chepkoech.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import dev.chepkoech.workoutlog.databinding.ActivitySignupBinding
import org.w3c.dom.Text

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        fun validate(){
            var firstName = binding.etFirstName.text.toString()
            var lastName = binding.etLastName.text.toString()
            var email = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()
            var confirmPassword = binding.etConfirmPassword.text.toString()


            if (firstName.isBlank()){
                binding.tilFirstName.error = "Please input  First Name"
            }
            if (lastName.isBlank()){
                binding.tilLastName.error = "Please input Last Name"
            }
            if (email.isBlank()){
                binding.tilEmail.error = "Please input email"
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                binding.tilEmail.error = "Emails do not match"
            if (password.isBlank()){
                binding.tilPassword.error = "Please input password"
            }
            if (confirmPassword.isBlank()){
                binding.tilConfirmPassword.error = "Please confirm your password"
            }
            if (confirmPassword != password){
                binding.tilConfirmPassword.error = "Please enter the same password"
            }
        }
    }




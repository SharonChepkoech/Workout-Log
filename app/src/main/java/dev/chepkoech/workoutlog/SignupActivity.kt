package dev.chepkoech.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text

class SignupActivity : AppCompatActivity() {

    lateinit var tilFirstName: TextInputLayout
    lateinit var tilLastName: TextInputLayout
    lateinit var etFirstName: EditText
    lateinit var etLastName: EditText
    lateinit var tilEmail: TextInputLayout
    lateinit var tilPassword: TextInputLayout
    lateinit var tilConfirmPassword: TextInputLayout
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var etConfirmPassword: EditText
    lateinit var tvLogin: TextView
    lateinit var btnSignUp: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        tilFirstName = findViewById(R.id.tilFirstName)
        tilLastName = findViewById(R.id.tilLastName)
        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        tilEmail = findViewById(R.id.tilEmail)
        tilPassword = findViewById(R.id.tilPassword)
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etPassword)
        tvLogin = findViewById(R.id.tvLogin)
        btnSignUp = findViewById(R.id.btnSignUp)

        tvLogin.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        btnSignUp.setOnClickListener {
            validate()

        }
    }

        fun validate(){
            var firstName = etFirstName.text.toString()
            var lastName = etLastName.text.toString()
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()
            var confirmPassword =  etConfirmPassword.text.toString()

            if (firstName.isBlank()){
                tilFirstName.error = "Please input First Name"
            }
            if (lastName.isBlank()){
                tilLastName.error = "Please input Last Name"
            }
            if (email.isBlank()){
                tilEmail.error = "Please input email"
            }
            if (password.isBlank()){
                tilPassword.error = "Please input password"
            }
            if (confirmPassword.isBlank()){
                tilConfirmPassword.error = "Please confirm your password"
            }
        }
    }




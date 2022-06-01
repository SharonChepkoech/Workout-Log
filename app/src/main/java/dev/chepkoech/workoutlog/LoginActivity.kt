package dev.chepkoech.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    lateinit var tvSignUp: TextView
    lateinit var etPassword:EditText
    lateinit var etEmail:EditText
    lateinit var tilPassword:TextInputLayout
    lateinit var tilEmail:TextInputLayout
    lateinit var btnLogin:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvSignUp = findViewById(R.id.tvSignUp)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        tilEmail = findViewById(R.id.tilEmail)
        tilPassword = findViewById(R.id.tilPassword)
        btnLogin = findViewById(R.id.btnLogin)

        tvSignUp.setOnClickListener{
            intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener{
            validate()
        }
    }
    fun validate(){
        var email = etEmail.text.toString()
        var password = etPassword.text.toString()

        if (email.isBlank()) {
            tilEmail.error = "Please input email"
        }
        if (password.isBlank()){
            tilPassword.error = "Please input password"
        }
    }

}
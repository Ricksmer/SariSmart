package com.example.sarismart.screens.register

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sarismart.R
import com.example.sarismart.screens.login.LoginActivity

class RegisterActivity : Activity(), RegisterContract.View {
    @SuppressLint("MissingInflatedId")

    private lateinit var presenter:              RegisterPresenter
    private lateinit var etFullName:            EditText
    private lateinit var etUserName:            EditText
    private lateinit var etPassword:            EditText
    private lateinit var etConfirmPassword:     EditText
    private lateinit var btnRegister:           Button
    private lateinit var tvLogin:               TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        presenter         = RegisterPresenter(this, RegisterModel())
        etFullName        = findViewById<EditText>(R.id.etFullName)
        etUserName        = findViewById<EditText>(R.id.etUsername)
        etPassword        = findViewById<EditText>(R.id.etPassword)
        etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        btnRegister       = findViewById<Button>(R.id.btnRegister)
        tvLogin           = findViewById<TextView>(R.id.tvLoginOption)

        btnRegister.setOnClickListener {
            presenter.onRegisterClicked(
                etFullName.text.toString(),
                etUserName.text.toString(),
                etPassword.text.toString(),
                etConfirmPassword.text.toString()
            )
        }

        tvLogin.setOnClickListener {
            presenter.onLoginClicked()
        }
    }

    override fun showSuccess() {
        Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()
    }
    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    override fun finishScreen() {
        finish()
    }

    override fun navigateToRegister() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
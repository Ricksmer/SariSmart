package com.example.sarismart.screens.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sarismart.R
import com.example.sarismart.screens.dashboard.DashboardActivity
import com.example.sarismart.screens.register.RegisterActivity

class LoginActivity : Activity(), LoginContract.View {
    @SuppressLint("MissingInflatedId")

    private lateinit var presenter:     LoginPresenter
    private lateinit var etUserName:    EditText
    private lateinit var etPassword:    EditText
    private lateinit var btnLogin:      Button
    private lateinit var tvRegister:    TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this, LoginModel())
        etUserName = findViewById<EditText>(R.id.etUserName)
        etPassword = findViewById<EditText>(R.id.etPassword)
        btnLogin   = findViewById<Button>(R.id.btnLogin)
        tvRegister = findViewById<TextView>(R.id.tvRegisterOption)

        btnLogin.setOnClickListener {
            presenter.onLoginClicked(
                etUserName.text.toString(),
                etPassword.text.toString())
        }

        tvRegister.setOnClickListener {
            presenter.onRegisterClicked()
        }
    }

    override fun showSuccess() {
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
    }
    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    override fun navigateToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    override fun navigateToDashboard(username: String) {
        startActivity(Intent(this, DashboardActivity::class.java))
    }
}

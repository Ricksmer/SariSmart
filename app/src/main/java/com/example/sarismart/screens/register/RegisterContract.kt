package com.example.sarismart.screens.register

interface RegisterContract {
    interface View {
        fun showSuccess()
        fun showError(message: String)
        fun finishScreen()

        fun navigateToRegister()
    }
    interface Presenter {
        fun onRegisterClicked(fullname: String, username: String, password: String, confirmPassword: String)
        fun onLoginClicked()
    }
}
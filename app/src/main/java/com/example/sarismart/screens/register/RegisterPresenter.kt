package com.example.sarismart.screens.register

class RegisterPresenter(private val view: RegisterContract.View,private val model: RegisterModel
) : RegisterContract.Presenter {
    override fun onRegisterClicked(fullname: String, username: String, password: String, confirmPassword: String) {
        if (fullname.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            view.showError("Fields cannot be empty")
            return
        }

        if (password != confirmPassword) {
            view.showError("Password does not match")
            return
        }

        val success = model.register(fullname, username, password)
        if (success) {
            view.showSuccess()
            view.finishScreen()
        } else {
            view.showError("User already exists")
        }
    }

    override fun onLoginClicked() {
        view.navigateToRegister()
    }
}


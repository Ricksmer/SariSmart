package com.example.sarismart.screens.login

import com.example.sarismart.data.repositories.UserRepository

class LoginModel {
    fun login(username: String, password: String): Boolean {
        return UserRepository.login(username, password)
    }
}

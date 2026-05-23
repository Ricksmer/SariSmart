package com.example.sarismart.screens.register

import com.example.sarismart.data.repositories.UserRepository

class RegisterModel {
    fun register(fullname: String, username: String, password: String): Boolean {
        return UserRepository.register(fullname, username, password)
    }
}
package com.example.sarismart.screens.profile

import com.example.sarismart.data.models.User
import com.example.sarismart.data.repositories.UserRepository

class ProfileModel {
    fun getCurrentUser(): User? {
        return UserRepository.getUser()
    }
}
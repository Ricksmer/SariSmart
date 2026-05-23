package com.example.sarismart.screens.dashboard

import com.example.sarismart.data.models.User
import com.example.sarismart.data.repositories.UserRepository

class DashboardModel {
    fun getCurrentUser(): User? {
        return UserRepository.getUser()
    }
}
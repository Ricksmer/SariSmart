package com.example.sarismart.screens.dashboard

import com.example.sarismart.data.models.Product
import com.example.sarismart.data.models.User
import com.example.sarismart.data.repositories.UserRepository

class DashboardModel {

    fun getCurrentUser(): User? {
        return UserRepository.getUser()
    }

    fun getProducts(): List<Product> {
        return UserRepository.getUser()?.products ?: emptyList()
    }

    fun getTotalProducts(): Int {
        return getProducts().size
    }

    fun getTotalCategories(): Int {
        return getProducts()
            .map { it.category }
            .filter { it.isNotBlank() }
            .distinct()
            .size
    }
}

package com.example.sarismart.data.repositories

import com.example.sarismart.data.models.Product

object ProductRepository {

    fun enlist(name: String, details: String, category: String, quantity: Int, price: Double) {
        val products = getProducts()
        val existing = products.find { it.name == name && it.category == category }

        if (existing != null) {
            existing.quantity += quantity
            existing.price = price
            existing.details = details;
        } else {
            val newId = if (products.isEmpty()) 0 else products.maxOf { it.id } + 1
            products.add(Product(newId, name, details, category, quantity, price))
        }
    }

    fun remove(name: String, category: String): Boolean {
        val products = getProducts()
        val target = products.find { it.name == name && it.category == category }
        return if (target != null) {
            products.remove(target)
            true
        } else {
            false
        }
    }

    fun getProducts(): MutableList<Product> {
        return UserRepository.getUser()?.products
            ?: throw IllegalStateException("No user is logged in.")
    }

}
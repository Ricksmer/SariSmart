package com.example.sarismart.screens.inventory

import com.example.sarismart.data.models.Product
import com.example.sarismart.data.repositories.ProductRepository
import com.example.sarismart.data.repositories.UserRepository

class InventoryModel {
    fun getProducts(): List<Product> {
        return UserRepository.getUser()?.products ?: emptyList()
    }

    fun deleteProduct(product: Product) {
        ProductRepository.remove(product.name, product.category)
    }
}
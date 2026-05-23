package com.example.sarismart.screens.addproduct

import com.example.sarismart.data.models.Product
import com.example.sarismart.data.repositories.ProductRepository

class AddProductModel {

    fun saveProduct(name: String, details: String, category: String, quantity: Int, price: Double) {
        ProductRepository.enlist(name, details, category, quantity, price)
    }

    fun getProducts(): List<Product> {
        return ProductRepository.getProducts()
    }
}
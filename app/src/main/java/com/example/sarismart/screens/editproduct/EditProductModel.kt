package com.example.sarismart.screens.editproduct

import com.example.sarismart.data.models.Product
import com.example.sarismart.data.repositories.ProductRepository

class EditProductModel : EditProductContract.Model {

    override fun getProductById(productId: Int): Product? {
        return ProductRepository.getProducts().find { it.id == productId }
    }

    override fun updateProduct(
        productId: Int,
        name: String,
        details: String,
        category: String,
        quantity: Int,
        price: Double
    ): Boolean {
        val product = getProductById(productId) ?: return false

        product.name = name
        product.details = details
        product.category = category
        product.quantity = quantity
        product.price = price

        return true
    }
}

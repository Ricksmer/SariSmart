package com.example.sarismart.screens.addproduct

interface AddProductContract {

    interface View {
        fun showError(field: String, message: String)
        fun clearErrors()
        fun onProductAdded()
    }

    interface Presenter {
        fun addProduct(name: String, details: String, price: String, quantity: String, category: String)
    }

    interface Model {
        fun saveProduct(name: String, details: String, category: String, quantity: Int, price: Double)
        fun getProducts(): List<com.example.sarismart.data.models.Product>
    }
}
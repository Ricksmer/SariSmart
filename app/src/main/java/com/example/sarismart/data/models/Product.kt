package com.example.sarismart.data.models

data class Product(
    var id: Int = 0,
    var name: String = "",
    var details: String = "",
    var category: String = "",
    var quantity: Int = 0,
    var price: Double = 0.0
)

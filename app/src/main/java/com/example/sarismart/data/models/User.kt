package com.example.sarismart.data.models

data class User (
    val fullName: String = "",
    val userName: String = "",
    val password: String = "",
    val products: MutableList<Product> = mutableListOf()
)
package com.example.sarismart.screens.editproduct

import com.example.sarismart.data.models.Product

interface EditProductContract {

    interface View {
        fun showProduct(product: Product)
        fun showError(field: String, message: String)
        fun clearErrors()
        fun showProductNotFound()
        fun onProductUpdated()
        fun navigateToDashboard()
        fun navigateToProfile()
        fun navigateToAddProduct()
        fun navigateToInventory()
        fun confirmLogOut()
    }

    interface Presenter {
        fun loadProduct(productId: Int)
        fun updateProduct(
            productId: Int,
            name: String,
            details: String,
            price: String,
            quantity: String,
            category: String
        )

        fun onDashboardClicked()
        fun onProfileClicked()
        fun onAddProductClicked()
        fun onInventoryClicked()
        fun onLogOutClicked()
    }

    interface Model {
        fun getProductById(productId: Int): Product?
        fun updateProduct(
            productId: Int,
            name: String,
            details: String,
            category: String,
            quantity: Int,
            price: Double
        ): Boolean
    }
}

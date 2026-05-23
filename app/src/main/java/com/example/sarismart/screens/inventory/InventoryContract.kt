package com.example.sarismart.screens.inventory

import com.example.sarismart.data.models.Product

interface InventoryContract {
    interface View {
        fun displayProducts(products: List<Product>)
        fun navigateToDashboard()
        fun navigateToProfile()
        fun navigateToAddProduct()
        fun confirmLogOut()
        fun confirmDelete(product: Product)
        fun displaySelectedCategory(category: String)
    }

    interface Presenter {
        fun loadProducts()
        fun deleteProduct(product: Product)
        fun onDashboardClicked()
        fun onProfileClicked()
        fun onAddProductClicked()
        fun onLogOutClicked()
        fun onCategorySelected(category: String)
    }
}
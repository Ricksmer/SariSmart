package com.example.sarismart.screens.inventory

import com.example.sarismart.data.models.Product

class InventoryPresenter(
    private val view: InventoryContract.View,
    private val model: InventoryModel
) : InventoryContract.Presenter {

    private var selectedCategory = "All"

    override fun loadProducts() {
        displayFilteredProducts()
        view.displaySelectedCategory(selectedCategory)
    }

    override fun deleteProduct(product: Product) {
        model.deleteProduct(product)
        displayFilteredProducts()
    }

    override fun onCategorySelected(category: String) {
        selectedCategory = category
        view.displaySelectedCategory(category)
        displayFilteredProducts()
    }

    private fun displayFilteredProducts() {
        val products = model.getProducts()

        val filteredProducts = if (selectedCategory == "All") {
            products
        } else {
            products.filter { it.category == selectedCategory }
        }

        view.displayProducts(filteredProducts)
    }

    override fun onDashboardClicked() { view.navigateToDashboard() }
    override fun onProfileClicked() { view.navigateToProfile() }
    override fun onAddProductClicked() { view.navigateToAddProduct() }
    override fun onLogOutClicked() { view.confirmLogOut() }
}

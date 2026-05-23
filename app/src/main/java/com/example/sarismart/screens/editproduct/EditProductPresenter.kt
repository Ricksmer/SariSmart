package com.example.sarismart.screens.editproduct

class EditProductPresenter(
    private val view: EditProductContract.View,
    private val model: EditProductContract.Model
) : EditProductContract.Presenter {

    override fun loadProduct(productId: Int) {
        val product = model.getProductById(productId)

        if (product == null) {
            view.showProductNotFound()
        } else {
            view.showProduct(product)
        }
    }

    override fun updateProduct(
        productId: Int,
        name: String,
        details: String,
        price: String,
        quantity: String,
        category: String
    ) {
        view.clearErrors()

        val cleanName = name.trim()
        val cleanDetails = details.trim()
        val cleanCategory = category.trim()
        val parsedPrice = price.trim().toDoubleOrNull()
        val parsedQuantity = quantity.trim().toIntOrNull()

        var hasError = false

        if (cleanName.isEmpty()) {
            view.showError("name", "Product name is required")
            hasError = true
        }

        if (parsedPrice == null || parsedPrice <= 0.0) {
            view.showError("price", "Enter a valid price")
            hasError = true
        }

        if (parsedQuantity == null || parsedQuantity < 0) {
            view.showError("quantity", "Enter a valid quantity")
            hasError = true
        }

        if (cleanCategory.isEmpty()) {
            view.showError("category", "Category is required")
            hasError = true
        }

        if (hasError) return

        val updated = model.updateProduct(
            productId = productId,
            name = cleanName,
            details = cleanDetails,
            category = cleanCategory,
            quantity = parsedQuantity!!,
            price = parsedPrice!!
        )

        if (updated) {
            view.onProductUpdated()
        } else {
            view.showProductNotFound()
        }
    }

    override fun onDashboardClicked() {
        view.navigateToDashboard()
    }

    override fun onProfileClicked() {
        view.navigateToProfile()
    }

    override fun onAddProductClicked() {
        view.navigateToAddProduct()
    }

    override fun onInventoryClicked() {
        view.navigateToInventory()
    }

    override fun onLogOutClicked() {
        view.confirmLogOut()
    }
}
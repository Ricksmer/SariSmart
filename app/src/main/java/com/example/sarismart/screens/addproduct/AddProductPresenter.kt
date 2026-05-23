package com.example.sarismart.screens.addproduct

class AddProductPresenter(
    private val view: AddProductContract.View,
    private val model: AddProductModel
) : AddProductContract.Presenter {

    override fun addProduct(
        name: String,
        details: String,
        price: String,
        quantity: String,
        category: String
    ) {
        view.clearErrors()

        if (name.isBlank()) {
            view.showError("name", "Product name is required.")
            return
        }
        if (price.isBlank()) {
            view.showError("price", "Price is required.")
            return
        }
        val parsedPrice = price.toDoubleOrNull()
        if (parsedPrice == null || parsedPrice <= 0.0) {
            view.showError("price", "Enter a price greater than 0.")
            return
        }
        if (quantity.isBlank()) {
            view.showError("quantity", "Quantity is required.")
            return
        }
        val parsedQuantity = quantity.toIntOrNull()
        if (parsedQuantity == null || parsedQuantity < 0) {
            view.showError("quantity", "Enter a quantity of 0 or more.")
            return
        }
        if (category.isBlank()) {
            view.showError("category", "Please select a category.")
            return
        }

        model.saveProduct(name.trim(), details.trim(), category, parsedQuantity, parsedPrice)
        view.onProductAdded()
    }
}
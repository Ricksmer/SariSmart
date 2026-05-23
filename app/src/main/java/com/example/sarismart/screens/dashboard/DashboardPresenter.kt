package com.example.sarismart.screens.dashboard

class DashboardPresenter(
    private val view: DashboardContract.View,
    private val model: DashboardModel
) : DashboardContract.Presenter {

    override fun loadDashboard() {
        val username = model.getCurrentUser()?.userName ?: "User"
        val totalProducts = model.getTotalProducts()
        val totalCategories = model.getTotalCategories()

        view.displayUserName(username)
        view.displaySummary(totalProducts, totalCategories)
    }

    override fun onProfileClicked() {
        view.navigateToProfile()
    }

    override fun onInventoryClicked() {
        view.navigateToInventory()
    }

    override fun onAddProductClicked() {
        view.navigateToAddProduct()
    }

    override fun onLogOutClicked() {
        view.confirmLogOut()
    }
}

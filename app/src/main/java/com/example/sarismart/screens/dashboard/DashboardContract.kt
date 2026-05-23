package com.example.sarismart.screens.dashboard

interface DashboardContract {

    interface View {
        fun displayUserName(username: String)
        fun displaySummary(totalProducts: Int, totalCategories: Int)
        fun navigateToProfile()
        fun navigateToInventory()
        fun navigateToAddProduct()
        fun confirmLogOut()
    }

    interface Presenter {
        fun loadDashboard()
        fun onProfileClicked()
        fun onInventoryClicked()
        fun onAddProductClicked()
        fun onLogOutClicked()
    }
}

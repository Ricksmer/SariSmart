package com.example.sarismart.screens.profile

interface ProfileContract {
    interface View {
        fun navigateToInventory()
        fun navigateToDashboard()

        fun navigateToAddProduct()
        fun confirmLogOut()
    }
    interface Presenter {
        fun onDashboardClicked()
        fun onInventoryClicked()
        fun onAddProductClicked()
        fun onLogOutClicked()
    }
}
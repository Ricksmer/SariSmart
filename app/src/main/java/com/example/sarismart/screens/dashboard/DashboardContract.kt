package com.example.sarismart.screens.dashboard

interface DashboardContract {

    interface View {
        fun navigateToProfile()
        fun navigateToInventory()
        fun navigateToAddProduct()
        fun confirmLogOut()
    }
    interface Presenter {
        fun onProfileClicked()
        fun onInventoryClicked()
        fun onAddProductClicked()
        fun onLogOutClicked()
    }
}
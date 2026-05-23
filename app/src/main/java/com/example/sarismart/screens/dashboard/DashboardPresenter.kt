package com.example.sarismart.screens.dashboard

import com.example.sarismart.data.models.User

class DashboardPresenter(private val view: DashboardContract.View,private val model: DashboardModel
) : DashboardContract.Presenter {
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

    fun getCurrentUser(): User? {
        return model.getCurrentUser()
    }
}
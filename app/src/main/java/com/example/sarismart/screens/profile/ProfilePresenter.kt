package com.example.sarismart.screens.profile

import com.example.sarismart.data.models.User

class ProfilePresenter(private val view: ProfileContract.View,private val model: ProfileModel
) : ProfileContract.Presenter {
    override fun onDashboardClicked() {
        view.navigateToDashboard()
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
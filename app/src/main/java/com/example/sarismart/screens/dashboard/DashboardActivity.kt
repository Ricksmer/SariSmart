package com.example.sarismart.screens.dashboard

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.sarismart.screens.login.LoginActivity
import com.example.sarismart.screens.profile.ProfileActivity
import com.example.sarismart.screens.inventory.InventoryActivity
import com.example.sarismart.screens.addproduct.AddProductActivity
import com.example.sarismart.R

class DashboardActivity : Activity() , DashboardContract.View {

    private lateinit var presenter:             DashboardPresenter
    private lateinit var tvUserName:            TextView
    private lateinit var btnProfile:            ImageView
    private lateinit var btnInventory:          ImageView
    private lateinit var btnAddProduct:         ImageView
    private lateinit var btnLogOut:             ImageView
    private lateinit var layoutViewInventory:   LinearLayout
    private lateinit var tvTotalProducts:       TextView
    private lateinit var tvTotalCategories:     TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        presenter = DashboardPresenter(this, DashboardModel())

        tvUserName = findViewById<TextView>(R.id.tvUserName)
        tvTotalProducts = findViewById(R.id.tvTotalProducts)
        tvTotalCategories = findViewById(R.id.tvTotalCategories)

        btnProfile = findViewById<ImageView>(R.id.btnProfile)
        btnInventory = findViewById<ImageView>(R.id.btnInventory)
        btnAddProduct = findViewById<ImageView>(R.id.btnAddProduct)
        btnLogOut = findViewById<ImageView>(R.id.btnLogOut)
        layoutViewInventory = findViewById(R.id.layoutViewInventory)

        // Display Username

        // Dashboard → Profile
        btnProfile.setOnClickListener {
            presenter.onProfileClicked()
        }

        // Dashboard → Inventory
        btnInventory.setOnClickListener {
            presenter.onInventoryClicked()
        }

        layoutViewInventory.setOnClickListener {
            presenter.onInventoryClicked()
        }

        btnAddProduct.setOnClickListener {
            presenter.onAddProductClicked()
        }

        // Logout
        btnLogOut.setOnClickListener {
            presenter.onLogOutClicked()
        }

        presenter.loadDashboard()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadDashboard()
    }

    override fun displayUserName(username: String) {
        tvUserName.text = "Hello, $username!"
    }

    override fun displaySummary(totalProducts: Int, totalCategories: Int) {
        tvTotalProducts.text = totalProducts.toString()
        tvTotalCategories.text = totalCategories.toString()
    }

    override fun navigateToProfile() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }
    override fun navigateToInventory() {
        startActivity(Intent(this, InventoryActivity::class.java))
    }
    override fun navigateToAddProduct() {
        startActivity(Intent(this, AddProductActivity::class.java))
    }
    override fun confirmLogOut(){
        AlertDialog.Builder(this)
        .setTitle("Log Out")
        .setMessage("Are you sure you want to log out?")
        .setPositiveButton("Log Out") { _, _ ->
            getSharedPreferences("user_prefs", MODE_PRIVATE).edit()
                .clear()
                .apply()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        .setNegativeButton("Cancel", null)
        .show()
    }
}
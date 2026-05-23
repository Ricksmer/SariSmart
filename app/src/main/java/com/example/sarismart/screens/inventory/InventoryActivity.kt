package com.example.sarismart.screens.inventory

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.example.sarismart.R
import com.example.sarismart.data.models.Product
import com.example.sarismart.screens.addproduct.AddProductActivity
import com.example.sarismart.screens.dashboard.DashboardActivity
import com.example.sarismart.screens.login.LoginActivity
import com.example.sarismart.screens.profile.ProfileActivity
import com.example.sarismart.screens.editproduct.EditProductActivity
import android.graphics.Color
import android.widget.TextView

class InventoryActivity : Activity(), InventoryContract.View {

    private lateinit var presenter: InventoryPresenter
    private lateinit var lvProducts: ListView
    private lateinit var btnDashboard: ImageView
    private lateinit var btnProfile: ImageView
    private lateinit var btnAddProduct: ImageView
    private lateinit var btnLogOut: ImageView
    private lateinit var categoryButtons: Map<String, TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        presenter = InventoryPresenter(this, InventoryModel())

        lvProducts    = findViewById(R.id.lvProducts)
        btnDashboard  = findViewById(R.id.btnDashboard)
        btnProfile    = findViewById(R.id.btnProfile)
        btnAddProduct = findViewById(R.id.btnAddProduct)
        btnLogOut     = findViewById(R.id.btnLogOut)

        btnDashboard.setOnClickListener  { presenter.onDashboardClicked() }
        btnProfile.setOnClickListener    { presenter.onProfileClicked() }
        btnAddProduct.setOnClickListener { presenter.onAddProductClicked() }
        btnLogOut.setOnClickListener     { presenter.onLogOutClicked() }

        setupCategoryFilters()
        presenter.loadProducts()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadProducts() // refresh when coming back from AddProduct or EditProduct
    }

    override fun displayProducts(products: List<Product>) {
        val adapter = ProductAdapter(
            context   = this,
            products  = products,
            onEdit    = { product -> navigateToEditProduct(product) },
            onDelete  = { product -> confirmDelete(product) }
        )
        lvProducts.adapter = adapter
    }

    override fun confirmDelete(product: Product) {
        AlertDialog.Builder(this)
            .setTitle("Delete Product")
            .setMessage("Delete \"${product.name}\"? This cannot be undone.")
            .setPositiveButton("Delete") { _, _ ->
                presenter.deleteProduct(product)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun displaySelectedCategory(category: String) {
        categoryButtons.forEach { (buttonCategory, button) ->
            if (buttonCategory == category) {
                button.setBackgroundResource(R.drawable.bg_filter_chip_selected)
                button.setTextColor(Color.WHITE)
            } else {
                button.setBackgroundResource(R.drawable.bg_filter_chip)
                button.setTextColor(Color.parseColor("#1A7949"))
            }
        }
    }
    private fun setupCategoryFilters() {
        categoryButtons = mapOf(
            "All" to findViewById(R.id.filterAll),
            "Food" to findViewById(R.id.filterFood),
            "Snacks" to findViewById(R.id.filterSnacks),
            "Instant Goods" to findViewById(R.id.filterInstantGoods),
            "Beverage" to findViewById(R.id.filterBeverage),
            "Personal Care" to findViewById(R.id.filterPersonalCare),
            "Cooking Ingredients" to findViewById(R.id.filterCookingIngredients),
            "Cleaning nd Laundry" to findViewById(R.id.filterCleaningSupplies),
            "Household" to findViewById(R.id.filterHousehold),
            "Tobacco and Alcohol" to findViewById(R.id.filterTobaccoAlcohol),
            "Other" to findViewById(R.id.filterOther)
        )

        categoryButtons.forEach { (category, button) ->
            button.setOnClickListener {
                presenter.onCategorySelected(category)
            }
        }
    }

    private fun navigateToEditProduct(product: Product) {
        val intent = Intent(this, EditProductActivity::class.java)
        intent.putExtra("product_id", product.id)
        startActivity(intent)
    }

    override fun navigateToDashboard() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }

    override fun navigateToProfile() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }

    override fun navigateToAddProduct() {
        startActivity(Intent(this, AddProductActivity::class.java))
    }

    override fun confirmLogOut() {
        AlertDialog.Builder(this)
            .setTitle("Log Out")
            .setMessage("Are you sure you want to log out?")
            .setPositiveButton("Log Out") { _, _ ->
                getSharedPreferences("user_prefs", MODE_PRIVATE).edit().clear().apply()
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
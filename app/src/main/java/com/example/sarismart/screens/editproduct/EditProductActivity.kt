package com.example.sarismart.screens.editproduct

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.sarismart.R
import com.example.sarismart.data.models.Product
import com.example.sarismart.screens.addproduct.AddProductActivity
import com.example.sarismart.screens.addproduct.CategoryBottomSheet
import com.example.sarismart.screens.dashboard.DashboardActivity
import com.example.sarismart.screens.inventory.InventoryActivity
import com.example.sarismart.screens.login.LoginActivity
import com.example.sarismart.screens.profile.ProfileActivity

class EditProductActivity : AppCompatActivity(), EditProductContract.View {

    private lateinit var presenter: EditProductContract.Presenter

    private lateinit var etName: EditText
    private lateinit var etDescription: EditText
    private lateinit var etPrice: EditText
    private lateinit var etQuantity: EditText
    private lateinit var tvCategory: TextView
    private lateinit var layoutCategory: LinearLayout
    private lateinit var btnSave: Button

    private lateinit var btnDashboard: ImageView
    private lateinit var btnProfile: ImageView
    private lateinit var btnAddProduct: ImageView
    private lateinit var btnInventory: ImageView
    private lateinit var btnLogOut: ImageView

    private var productId = -1
    private var selectedCategory = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editproduct)

        presenter = EditProductPresenter(this, EditProductModel())
        productId = intent.getIntExtra("product_id", -1)

        etName = findViewById(R.id.etProductName)
        etDescription = findViewById(R.id.etProductDescription)
        etPrice = findViewById(R.id.etProductPrice)
        etQuantity = findViewById(R.id.etProductQuantity)
        tvCategory = findViewById(R.id.tvCategory)
        layoutCategory = findViewById(R.id.layoutCategory)
        btnSave = findViewById(R.id.btnSave)

        btnDashboard = findViewById(R.id.btnDashboard)
        btnProfile = findViewById(R.id.btnProfile)
        btnAddProduct = findViewById(R.id.btnAddProduct)
        btnInventory = findViewById(R.id.btnInventory)
        btnLogOut = findViewById(R.id.btnLogOut)

        layoutCategory.setOnClickListener {
            CategoryBottomSheet { category ->
                selectedCategory = category
                tvCategory.text = category
            }.show(supportFragmentManager, "CategoryBottomSheet")
        }

        btnSave.setOnClickListener {
            presenter.updateProduct(
                productId = productId,
                name = etName.text.toString(),
                details = etDescription.text.toString(),
                price = etPrice.text.toString(),
                quantity = etQuantity.text.toString(),
                category = selectedCategory
            )
        }

        btnDashboard.setOnClickListener { presenter.onDashboardClicked() }
        btnProfile.setOnClickListener { presenter.onProfileClicked() }
        btnAddProduct.setOnClickListener { presenter.onAddProductClicked() }
        btnInventory.setOnClickListener { presenter.onInventoryClicked() }
        btnLogOut.setOnClickListener { presenter.onLogOutClicked() }

        presenter.loadProduct(productId)
    }

    override fun showProduct(product: Product) {
        selectedCategory = product.category

        etName.setText(product.name)
        etDescription.setText(product.details)
        etPrice.setText(product.price.toString())
        etQuantity.setText(product.quantity.toString())
        tvCategory.text = product.category
    }

    override fun showError(field: String, message: String) {
        when (field) {
            "name" -> etName.error = message
            "price" -> etPrice.error = message
            "quantity" -> etQuantity.error = message
            "category" -> tvCategory.hint = message
        }
    }

    override fun clearErrors() {
        etName.error = null
        etPrice.error = null
        etQuantity.error = null
        tvCategory.hint = "Category"
    }

    override fun showProductNotFound() {
        Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onProductUpdated() {
        Toast.makeText(this, "Product updated!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, InventoryActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
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
        finish()
    }

    override fun navigateToInventory() {
        startActivity(Intent(this, InventoryActivity::class.java))
        finish()
    }

    override fun confirmLogOut() {
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
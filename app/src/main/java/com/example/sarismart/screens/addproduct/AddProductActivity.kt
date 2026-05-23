package com.example.sarismart.screens.addproduct

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
import com.example.sarismart.screens.dashboard.DashboardActivity
import com.example.sarismart.screens.inventory.InventoryActivity
import com.example.sarismart.screens.login.LoginActivity
import com.example.sarismart.screens.profile.ProfileActivity

class AddProductActivity : AppCompatActivity(), AddProductContract.View {

    private lateinit var presenter: AddProductContract.Presenter

    private lateinit var etName: EditText
    private lateinit var etDescription: EditText
    private lateinit var etPrice: EditText
    private lateinit var etQuantity: EditText
    private lateinit var tvCategory: TextView
    private lateinit var layoutCategory: LinearLayout
    private lateinit var btnAdd: Button

    private lateinit var btnDashboard: ImageView
    private lateinit var btnProfile: ImageView
    private lateinit var btnAddProduct: ImageView
    private lateinit var btnInventory: ImageView
    private lateinit var btnLogOut: ImageView

    private var selectedCategory = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addproduct)

        val model = AddProductModel()
        presenter = AddProductPresenter(this, model)

        // Form fields
        etName         = findViewById(R.id.etProductName)
        etDescription  = findViewById(R.id.etProductDescription)
        etPrice        = findViewById(R.id.etProductPrice)
        etQuantity     = findViewById(R.id.etProductQuantity)
        tvCategory     = findViewById(R.id.tvCategory)
        layoutCategory = findViewById(R.id.layoutCategory)
        btnAdd         = findViewById(R.id.btnAdd)

        // Nav buttons
        btnDashboard   = findViewById(R.id.btnDashboard)
        btnProfile     = findViewById(R.id.btnProfile)
        btnAddProduct  = findViewById(R.id.btnAddProduct)
        btnInventory   = findViewById(R.id.btnInventory)
        btnLogOut      = findViewById(R.id.btnLogOut)

        // Category picker
        layoutCategory.setOnClickListener {
            CategoryBottomSheet { category ->
                selectedCategory = category
                tvCategory.text = category
            }.show(supportFragmentManager, "CategoryBottomSheet")
        }

        // Add product
        btnAdd.setOnClickListener {
            presenter.addProduct(
                name     = etName.text.toString(),
                details  = etDescription.text.toString(),
                price    = etPrice.text.toString(),
                quantity = etQuantity.text.toString(),
                category = selectedCategory
            )
        }

        // Navigation
        btnDashboard.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }

        btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        btnAddProduct.setOnClickListener {
            // Already here, do nothing
        }

        btnInventory.setOnClickListener {
            startActivity(Intent(this, InventoryActivity::class.java))
        }

        btnLogOut.setOnClickListener {
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

    override fun showError(field: String, message: String) {
        when (field) {
            "name"     -> etName.error = message
            "price"    -> etPrice.error = message
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

    override fun onProductAdded() {
        Toast.makeText(this, "Product added!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, InventoryActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}
package com.example.sarismart.screens.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.sarismart.R
import com.example.sarismart.data.models.User
import com.example.sarismart.screens.addproduct.AddProductActivity
import com.example.sarismart.screens.dashboard.DashboardActivity
import com.example.sarismart.screens.inventory.InventoryActivity
import com.example.sarismart.screens.login.LoginActivity

class ProfileActivity : Activity(), ProfileContract.View {

    private lateinit var presenter:         ProfilePresenter
    private          var currentUser:       User? = null
    private lateinit var tvUserName:        TextView
    private lateinit var btnInventory:      ImageView
    private lateinit var btnDashboard:      ImageView
    private lateinit var btnLogOut:         ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        presenter = ProfilePresenter(this, ProfileModel())
        currentUser = presenter.getCurrentUser()

        tvUserName = findViewById<TextView>(R.id.tvUserName)
        btnDashboard = findViewById<ImageView>(R.id.btnDashboard)
        btnInventory = findViewById<ImageView>(R.id.btnInventory)
        btnLogOut = findViewById<ImageView>(R.id.btnLogOut)

        // Display Username
        val username = currentUser?.userName
        tvUserName.text = "Hello, $username!"

        // Navigation Listeners
        btnDashboard.setOnClickListener {
            presenter.onDashboardClicked()
        }

        btnInventory.setOnClickListener {
            presenter.onInventoryClicked()
        }

        btnLogOut.setOnClickListener {
            presenter.onLogOutClicked()
        }
    }

    override fun navigateToInventory(){
        startActivity(Intent(this, InventoryActivity::class.java))
    }

    override fun navigateToAddProduct(){
        startActivity(Intent(this, AddProductActivity::class.java))
    }

    override fun navigateToDashboard(){
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
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

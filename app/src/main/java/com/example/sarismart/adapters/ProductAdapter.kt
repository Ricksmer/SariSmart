package com.example.sarismart.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sarismart.R
import com.example.sarismart.data.models.Product

class ProductAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvProductName)
        val tvCategory: TextView = view.findViewById(R.id.tvProductCategory)
        val tvPrice: TextView = view.findViewById(R.id.tvProductPrice)
        val tvQuantity: TextView = view.findViewById(R.id.tvProductQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.tvName.text = product.name
        holder.tvCategory.text = product.category
        holder.tvPrice.text = "₱${String.format("%.2f", product.price)}"
        holder.tvQuantity.text = "Qty: ${product.quantity}"
    }

    override fun getItemCount() = products.size
}
package com.example.sarismart.screens.inventory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.sarismart.R
import com.example.sarismart.data.models.Product

class ProductAdapter(
    private val context: Context,
    private val products: List<Product>,
    private val onEdit: (Product) -> Unit,
    private val onDelete: (Product) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = products.size
    override fun getItem(position: Int): Any = products[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_product_list, parent, false)

        val product = getItem(position) as Product

        view.findViewById<TextView>(R.id.tvItemName).text = product.name
        view.findViewById<TextView>(R.id.tvItemPrice).text = "₱${String.format("%.2f", product.price)}"
        view.findViewById<TextView>(R.id.tvItemQuantity).text = "Qty: ${product.quantity}"
        view.findViewById<TextView>(R.id.tvItemCategory).text = product.category

        view.findViewById<ImageView>(R.id.btnEdit).setOnClickListener { onEdit(product) }
        view.findViewById<ImageView>(R.id.btnDelete).setOnClickListener { onDelete(product) }

        return view
    }
}
package com.example.sarismart.screens.addproduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sarismart.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CategoryBottomSheet(
    private val onCategorySelected: (String) -> Unit
) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.bottom_sheet_category, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val categories = mapOf(
            R.id.catFood                to "Food",
            R.id.catSnacks              to "Snacks",
            R.id.catInstantGoods        to "Instant Goods",
            R.id.catBeverage            to "Beverage",
            R.id.catPersonalCare        to "Personal Care",
            R.id.catCookingIngredients  to "Cooking Ingredients",
            R.id.catCleaningSupplies    to "Cleaning nd Laundry",
            R.id.catHousehold           to "Household",
            R.id.catTobaccoAlcohol      to "Tobacco and Alcohol",
            R.id.catOther               to "Other"
        )

        categories.forEach { (id, label) ->
            view.findViewById<TextView>(id).setOnClickListener {
                onCategorySelected(label)
                dismiss()
            }
        }
    }
}
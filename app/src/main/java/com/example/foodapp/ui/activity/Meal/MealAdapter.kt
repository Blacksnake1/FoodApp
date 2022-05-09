package com.example.foodapp.ui.activity.Meal

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.data.pojo.FilterCategoryModel

class MealAdapter (
    var context: Context,
    var listMeal : MutableList<FilterCategoryModel>,
    var onClickItem : (item:FilterCategoryModel) -> Unit
): RecyclerView.Adapter<MealAdapter.ViewHolder>() {
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgMeal = itemView.findViewById<ImageView>(R.id.img_meal)
        var tvMeal  = itemView.findViewById<TextView>(R.id.tv_meal)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}
package com.example.foodapp.ui.activity.Meal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.pojo.FilterCategoryModel

class TypeMealAdapter (
    var context: Context,
    var listMeal : MutableList<FilterCategoryModel>,
    var onClickItem : (item:FilterCategoryModel) -> Unit
): RecyclerView.Adapter<TypeMealAdapter.ViewHolder>() {
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgMeal = itemView.findViewById<ImageView>(R.id.img_meal)
        var tvMeal  = itemView.findViewById<TextView>(R.id.tv_meal)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.activity_meal_by_category,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var typeMeal = listMeal[position]
        Glide.with(context).load(typeMeal.strMealThumb).into(holder.imgMeal)
        holder.tvMeal.text = typeMeal.strMeal
        holder.itemView.setOnClickListener {
            onClickItem.invoke(typeMeal)

        }
    }

    override fun getItemCount(): Int = listMeal.size


}
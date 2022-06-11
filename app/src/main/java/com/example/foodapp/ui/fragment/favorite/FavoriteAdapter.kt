package com.example.foodapp.ui.fragment.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.data.MealDetail

class FavoriteAdapter(
    var context: Context,
    var listFavorite : MutableList<MealDetail>,
    var onClickItem : (item: MealDetail ) -> Unit
): RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    class ViewHolder(itemview:View ): RecyclerView.ViewHolder(itemview) {
        var imgFavourite = itemview.findViewById<ImageView>(R.id.img_meal)
        var tvFavourite = itemview.findViewById<TextView>(R.id.tv_meal)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_meal_by_category,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var typeFavorite = listFavorite[position]
        Glide.with(context).load(typeFavorite.strMealThumb).into(holder.imgFavourite)
        holder.tvFavourite.text = typeFavorite.strMeal
        holder.itemView.setOnClickListener {
            onClickItem.invoke(typeFavorite)
        }
    }
    override fun getItemCount(): Int = listFavorite.size
    }

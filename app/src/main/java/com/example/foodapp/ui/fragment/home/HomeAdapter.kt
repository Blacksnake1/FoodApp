package com.example.foodapp.ui.fragment.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.model.CategoryModel

class HomeAdapter(
    var context: Context,
    var listCategory: MutableList<CategoryModel>,
    var onClickItem: (item:CategoryModel) -> Unit
): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder ( itemView: View): RecyclerView.ViewHolder(itemView) {
        var img_item = itemView.findViewById<ImageView>(R.id.img_category)
        var tv_item  = itemView.findViewById<TextView>(R.id.tv_category)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_home_categories,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var itemCategory = listCategory. getOrNull(position)
        itemCategory?.strCategoryThumb?.let {
            Glide.with(context).load(it).into(holder.img_item)
        }
        holder.tv_item.text = itemCategory?.strCategory
        holder.itemView.setOnClickListener {
            if (itemCategory != null){
                onClickItem.invoke(itemCategory)
            }
        }
    }

    override fun getItemCount(): Int = listCategory.size


}
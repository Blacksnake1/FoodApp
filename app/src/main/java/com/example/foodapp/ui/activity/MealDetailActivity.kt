package com.example.foodapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.pojo.MealDetail

class MealDetailActivity : AppCompatActivity() {
    var mealDetail : MealDetail?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_detail)

        setupView()
        showView()

    }

    private fun showView() {
        if (setupView()!= null) {
            Glide.with(this).load(mealDetail?.strMealThumb).into()
        }
    }

    private fun setupView() {
        val mealDetail = intent.getSerializableExtra("a") as MealDetail
        Toast.makeText(this , mealDetail.strMeal ,Toast.LENGTH_SHORT).show()
    }


}
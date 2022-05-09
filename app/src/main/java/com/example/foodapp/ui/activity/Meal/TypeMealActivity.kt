package com.example.foodapp.ui.activity.Meal

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodapp.R

class TypeMealActivity : AppCompatActivity() {
    companion object {
        fun getIntent(context: Context, type: Int = 1): Intent {
            return Intent(context, TypeMealActivity::class.java).apply {
                putExtra("type", type)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_by_category)

    }
}
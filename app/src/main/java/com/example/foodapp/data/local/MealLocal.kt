package com.example.foodapp.data.local

import com.example.foodapp.FoodApplication
import com.example.foodapp.data.db.MealDatabase
import com.example.foodapp.data.model.MealDetail

class MealLocal {
    private val mealDatabase = MealDatabase.getInstance(FoodApplication.getInstance().applicationContext)


    fun saveFavoriteMeal(meal : MealDetail){
        mealDatabase.mealDao().insert(meal)
    }

    fun getFavoriteMeal() : MutableList<MealDetail> {
       return mealDatabase.mealDao().getAllMeal().toMutableList()
    }

    fun removeFavoriteMeal(meal : MealDetail) {
        mealDatabase.mealDao().delete(meal)
    }
}
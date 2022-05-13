package com.example.foodapp.data.local

import com.example.foodapp.FoodApplication
import com.example.foodapp.data.db.MealDatabase
import com.example.foodapp.data.pojo.MealDetail

class MealLocal {
    private val mealDatabase = MealDatabase.getInstance(FoodApplication.getInstance().applicationContext)


    fun saveFavoriteMeal(meal : MealDetail){
        mealDatabase.mealDao().upsert(meal)
    }

    fun getFavoriteMeal() : MutableList<MealDetail> {
       return mealDatabase.mealDao().getAllMeal().toMutableList()
    }

    fun removeFavoriteMeal(meal : MealDetail) {
        mealDatabase.mealDao().delete(meal)
    }
}
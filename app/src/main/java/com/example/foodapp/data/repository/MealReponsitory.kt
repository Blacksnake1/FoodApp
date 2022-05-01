package com.example.foodapp.data.repository

import com.example.foodapp.data.pojo.CategoryResponse
import com.example.foodapp.data.pojo.RandomMealResponse
import com.example.foodapp.data.remote.MealRemote
import io.reactivex.rxjava3.core.Single

class MealReponsitory(
    private var mealRemote: MealRemote = MealRemote()
) {
    fun getRandomMeal(): Single<RandomMealResponse> {
        return mealRemote.getRandomMeal()

    }
    fun getMealsByCategory (): Single<CategoryResponse>{
        return mealRemote.getMealsByCategory()
    }
}
package com.example.foodapp.data.repository

import com.example.foodapp.data.local.MealLocal
import com.example.foodapp.data.pojo.CategoryResponse
import com.example.foodapp.data.pojo.FilterCategoryResponse
import com.example.foodapp.data.pojo.MealDetail
import com.example.foodapp.data.pojo.RandomMealResponse
import com.example.foodapp.data.remote.MealRemote
import io.reactivex.rxjava3.core.Single

class MealReponsitory(
    private var mealRemote: MealRemote = MealRemote(),
    private var mealLocal : MealLocal = MealLocal()
) {
    fun getRandomMeal(): Single<RandomMealResponse> {
        return mealRemote.getRandomMeal()

    }

    fun getCategory (): Single<CategoryResponse>{
        return mealRemote.getMealsCategory()
    }

    fun getMealsByCatelogy(category:String) : Single<FilterCategoryResponse>{
        return  mealRemote.getMealsByCatelogy(category)
    }

    fun getMealById(id:String) : Single<RandomMealResponse>{
        return  mealRemote.getMealById(id)
    }

    fun saveFavoriteMeal(meal : MealDetail){
        mealLocal.saveFavoriteMeal(meal)
    }

    fun getFavoriteMeal() : MutableList<MealDetail> {
        return mealLocal.getFavoriteMeal()
    }

    fun removeFavoriteMeal(meal : MealDetail) {
        mealLocal.getFavoriteMeal().forEach {
            if (meal.idMeal == it.idMeal){
                mealLocal.removeFavoriteMeal(meal)
                return
            }
        }
    }
}
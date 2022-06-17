package com.example.foodapp.data.remote

import com.example.foodapp.data.model.CategoryResponse
import com.example.foodapp.data.model.FilterCategoryResponse
import com.example.foodapp.data.model.RandomMealResponse
import com.example.foodapp.data.retrofit.MealApi
import com.example.foodapp.data.retrofit.RetrofitClient
import com.example.foodapp.utils.Utils
import io.reactivex.rxjava3.core.Single

class MealRemote(
    private var apiMeal: MealApi = RetrofitClient.getInstance(Utils.BaseUrl)!!
        .create(MealApi::class.java),
) {
    fun getRandomMeal (): Single<RandomMealResponse> {
        return apiMeal.getRandomMeal()

    }
    fun getMealsCategory (): Single<CategoryResponse>{
        return apiMeal.getMealsCategory()
    }
    fun getMealsByCatelogy(category:String) : Single<FilterCategoryResponse>{
        return  apiMeal.getMealsByCatelogy(category)
    }

    fun getMealById(id:String) : Single<RandomMealResponse>{
        return  apiMeal.getMealById(id)
    }

}
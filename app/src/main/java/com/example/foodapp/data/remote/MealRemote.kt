package com.example.foodapp.data.remote

import com.example.foodapp.data.pojo.CategoryResponse
import com.example.foodapp.data.pojo.FilterCategoryResponse
import com.example.foodapp.data.pojo.RandomMealResponse
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

}
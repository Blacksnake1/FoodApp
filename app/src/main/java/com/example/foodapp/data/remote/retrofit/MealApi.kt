package com.example.foodapp.data.remote.retrofit


import com.example.foodapp.data.pojo.CategoryResponse
import com.example.foodapp.data.pojo.RandomMealResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MealApi {
    @GET ("random.php")
    fun getRandomMeal(): Single<RandomMealResponse>

    @GET("filter.php?")
    fun getMealsByCategory() : Single<CategoryResponse>

}
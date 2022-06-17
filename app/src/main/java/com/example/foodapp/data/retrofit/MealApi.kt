package com.example.foodapp.data.retrofit


import com.example.foodapp.data.model.CategoryResponse
import com.example.foodapp.data.model.FilterCategoryResponse
import com.example.foodapp.data.model.RandomMealResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET ("random.php")
    fun getRandomMeal(): Single<RandomMealResponse>

    @GET("categories.php")
    fun getMealsCategory() : Single<CategoryResponse>

    @GET("filter.php?")
    fun getMealsByCatelogy(
        @Query("c") category :String
    ) : Single<FilterCategoryResponse>

    @GET("lookup.php?")
    fun getMealById(
        @Query("i") id:String
    ): Single<RandomMealResponse>

}
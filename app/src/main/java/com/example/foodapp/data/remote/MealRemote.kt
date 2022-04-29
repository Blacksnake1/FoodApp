package com.example.foodapp.data.remote

import com.example.foodapp.data.pojo.CategoryResponse
import com.example.foodapp.data.pojo.RandomMealResponse
import com.example.foodapp.data.remote.retrofit.MealApi
import com.example.foodapp.data.remote.retrofit.RetrofitClient
import com.example.foodapp.utils.Utils
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import retrofit2.create

class MealRemote(
    private var apiMeal: MealApi= RetrofitClient.getInstance(Utils.BaseUrl)!!.create(MealApi::class.java)
) {
    fun getRandomMeal (): Single<RandomMealResponse> {
        return apiMeal.getRandomMeal()

    }
    fun getMealsByCategory (): Single<CategoryResponse>{
        return apiMeal.getMealsByCategory()
    }
}
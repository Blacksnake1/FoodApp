package com.example.foodapp.ui.activity.MealsDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.pojo.MealDetail
import com.example.foodapp.data.pojo.RandomMealResponse
import com.example.foodapp.data.repository.MealReponsitory

class MealDetailVM(
    private val mealDetailResponse: MealReponsitory = MealReponsitory()
): ViewModel() {
    var mealDetailLiveData = MutableLiveData<RandomMealResponse>()
    var errorLiveData = MutableLiveData<String>()

    fun isFavoriteMeal (meal: MealDetail): Boolean {
       return mealDetailResponse.getFavoriteMeal().any { it.idMeal == meal.idMeal }
    }

    fun saveFavoriteMeal(meal : MealDetail){
        mealDetailResponse.saveFavoriteMeal(meal)
    }

    fun removeFavoriteMeal(meal: MealDetail){
        mealDetailResponse.removeFavoriteMeal(meal)
    }
}
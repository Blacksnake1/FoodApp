package com.example.foodapp.ui.fragment.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.data.MealDetail
import com.example.foodapp.data.repository.MealReponsitory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteVM (
    private val mealReponsitory: MealReponsitory = MealReponsitory()
): ViewModel() {
    var mealList = MutableLiveData<MutableList<MealDetail>>()
    var isLoading = MutableLiveData<Boolean>()

    fun getFavorite() {
        isLoading.postValue(true)
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                isLoading.postValue(false)
                try {
                    mealList.postValue(mealReponsitory.getFavoriteMeal())

                } catch (e: Exception){

                }
            }
        }


    }
    fun removeFavorite (meal: MealDetail){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                mealReponsitory.removeFavoriteMeal(meal)
            }
        }

    }

    fun insertFavorite (meal: MealDetail) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mealReponsitory.saveFavoriteMeal(meal)
            }

        }
    }


}
package com.example.foodapp.ui.activity.MealsDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.pojo.MealDetail
import com.example.foodapp.data.pojo.RandomMealResponse
import com.example.foodapp.data.repository.MealReponsitory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MealDetailVM(
    private val mealDetailResponse: MealReponsitory = MealReponsitory()
) : ViewModel() {
    var mealDetailLiveData = MutableLiveData<RandomMealResponse>()
    var errorLiveData = MutableLiveData<String>()

    fun getMealDetail(id: String) {
        mealDetailResponse.getMealById(id)
            .subscribeOn(io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : SingleObserver<RandomMealResponse> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(randomMealResponse: RandomMealResponse) {
                    mealDetailLiveData.postValue(randomMealResponse)
                }

                override fun onError(e: Throwable) {
                    errorLiveData.postValue(e.message)
                }

            })

    }

    fun isFavoriteMeal(meal: MealDetail): Boolean {
        viewModelScope.launch {

        }
        return mealDetailResponse.getFavoriteMeal().any {
            it.idMeal == meal.idMeal
        }
    }

    fun handleIsFavorite(isFavorite: Boolean, meal: MealDetail) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (isFavorite) {
                    mealDetailResponse.saveFavoriteMeal(meal)
                } else {
                    mealDetailResponse.removeFavoriteMeal(meal)
                }
            }
        }


    }
}
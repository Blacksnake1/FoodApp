package com.example.foodapp.ui.activity.MealsDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.pojo.MealDetail
import com.example.foodapp.data.pojo.RandomMealResponse
import com.example.foodapp.data.repository.MealReponsitory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io

class MealDetailVM(
    private val mealDetailResponse: MealReponsitory = MealReponsitory()
): ViewModel() {
    var mealDetailLiveData = MutableLiveData<RandomMealResponse>()
    var errorLiveData = MutableLiveData<String>()

    fun getMealDetail (id: String ){
        mealDetailResponse.getMealById(id)
            .subscribeOn(io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object: SingleObserver<RandomMealResponse>{
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
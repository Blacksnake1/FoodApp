package com.example.foodapp.ui.fragment.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.data.CategoryResponse
import com.example.foodapp.data.data.RandomMealResponse
import com.example.foodapp.data.repository.MealReponsitory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeVM (
    var mealResponse: MealReponsitory = MealReponsitory(),
) :ViewModel() {

    var randomMealLiveData = MutableLiveData<RandomMealResponse> ()
    var categoryLiveData = MutableLiveData<CategoryResponse>()
    var errorLiveData = MutableLiveData<String>()

    fun getRandomMeal(){
        mealResponse.getRandomMeal()
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : SingleObserver<RandomMealResponse>{
                override fun onSubscribe(d: Disposable) {
                }

                override fun onSuccess(t: RandomMealResponse) {
                    randomMealLiveData.postValue(t)

                }

                override fun onError(e: Throwable) {
                    errorLiveData.postValue(e?.message)
                    Log.e("homeRandom", e.message.toString())

                }

            })
    }
    fun getCategory() {
        mealResponse.getCategory()
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : SingleObserver<CategoryResponse>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(t: CategoryResponse) {
                    categoryLiveData.postValue(t)
                }

                override fun onError(e: Throwable) {
                    errorLiveData.postValue(e.message)
                }

            })



    }





}
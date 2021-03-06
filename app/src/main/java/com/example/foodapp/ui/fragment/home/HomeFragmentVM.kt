package com.example.foodapp.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.model.CategoryResponse
import com.example.foodapp.data.model.RandomMealResponse
import com.example.foodapp.data.repository.MealReponsitory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeFragmentVM (var mealResponse: MealReponsitory = MealReponsitory()
) :ViewModel() {

    var randomMealLiveData = MutableLiveData<RandomMealResponse> ()
    var errorLiveData = MutableLiveData<String>()
    var categoryLiveData = MutableLiveData<CategoryResponse>()


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


                }


            })
    }

    fun getCatelog(){
        mealResponse.getMealsByCategory()
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : SingleObserver<CategoryResponse>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(t: CategoryResponse) {
                    categoryLiveData.postValue(t)
                }

                override fun onError(e: Throwable) {
                    errorLiveData.postValue(e.message).toString()
                }

            })

    }

}
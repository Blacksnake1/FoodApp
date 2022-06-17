package com.example.foodapp.ui.activity.meal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.model.FilterCategoryResponse
import com.example.foodapp.data.repository.MealReponsitory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class TypeMealVM(
    private val typeMealResponse: MealReponsitory = MealReponsitory()
) : ViewModel() {

    var typeMealLiveData = MutableLiveData<FilterCategoryResponse>()
    var errorLiveData    = MutableLiveData<String>()

    fun getTypeMeal (category : String){
        typeMealResponse.getMealsByCatelogy(category)
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object: SingleObserver<FilterCategoryResponse>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(t: FilterCategoryResponse) {
                    typeMealLiveData.postValue(t)

                }

                override fun onError(e: Throwable) {
                    errorLiveData.postValue(e.message)
                }


            })




    }



}
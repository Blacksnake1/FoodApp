package com.example.foodapp

import android.app.Application

class FoodApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        @Volatile
        private var instance: FoodApplication? = null

        @JvmStatic
        fun getInstance(): FoodApplication = instance ?: synchronized(this) {
            instance ?: FoodApplication().also {
                instance = it
            }
        }
    }
}
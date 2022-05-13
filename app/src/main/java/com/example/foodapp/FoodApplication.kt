package com.example.foodapp

import android.app.Application

class FoodApplication : Application() {

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
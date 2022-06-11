package com.example.foodapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodapp.data.data.MealDetail

@Database(entities = [MealDetail::class], version = 1)
@TypeConverters(MealTypeConverter::class)
abstract class MealDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao

    companion object{
        @Volatile
        var instance : MealDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MealDatabase{
            if(instance == null){
                instance = Room.databaseBuilder(context, MealDatabase::class.java, "meal.db")
                    .build()
            }
            return instance as MealDatabase
        }
    }
}
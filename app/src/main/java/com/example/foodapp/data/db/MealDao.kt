package com.example.foodapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.foodapp.data.pojo.MealDetail

@Dao
interface MealDao {
    @Insert ( onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(mealDetail: MealDetail)

    @Delete
    suspend fun delete (mealDetail: MealDetail)

    @Query ( "SELECT * FROM mealDetail ")
    fun getAllMeal():LiveData<List<MealDetail>>
}
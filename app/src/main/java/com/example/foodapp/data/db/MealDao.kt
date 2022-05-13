package com.example.foodapp.data.db

import androidx.room.*
import androidx.room.Dao
import com.example.foodapp.data.pojo.MealDetail

@Dao
interface MealDao {
    @Insert ( onConflict = OnConflictStrategy.REPLACE)
    fun upsert(mealDetail: MealDetail)

    @Delete
    fun delete (mealDetail: MealDetail)

    @Query ( "SELECT * FROM mealDetail ")
    fun getAllMeal(): List<MealDetail>
}
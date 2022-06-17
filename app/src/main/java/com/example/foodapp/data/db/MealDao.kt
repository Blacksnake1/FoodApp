package com.example.foodapp.data.db

import androidx.room.*
import androidx.room.Dao
import com.example.foodapp.data.model.MealDetail

@Dao
interface MealDao {
    @Insert ( onConflict = OnConflictStrategy.REPLACE)
    fun insert(mealDetail: MealDetail): Long

    @Delete
    fun delete (mealDetail: MealDetail)

    @Query ( "SELECT * FROM mealDetail ")
    fun getAllMeal(): List<MealDetail>
}
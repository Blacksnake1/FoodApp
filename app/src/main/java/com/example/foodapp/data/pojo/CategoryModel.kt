package com.example.foodapp.data.pojo

import java.io.Serializable

data class CategoryModel(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
): Serializable
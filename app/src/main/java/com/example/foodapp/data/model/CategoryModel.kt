package com.example.foodapp.data.model

import java.io.Serializable

data class CategoryModel(
    val idCategory: String?,
    val strCategory: String?,
    val strCategoryDescription: String?,
    val strCategoryThumb: String?
): Serializable
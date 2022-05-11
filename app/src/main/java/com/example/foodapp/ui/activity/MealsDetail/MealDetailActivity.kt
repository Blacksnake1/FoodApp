package com.example.foodapp.ui.activity.MealsDetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.pojo.MealDetail
import com.example.foodapp.ui.activity.Meal.TypeMealActivity
import kotlinx.android.synthetic.main.activity_meal_detail.*


class MealDetailActivity : AppCompatActivity() {
    var mealDetail: MealDetail? = null
    var meal = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_detail)

        initData()
        setupView()

    }

    private fun initData() {
        mealDetail = intent.getSerializableExtra("RANDOM_MEAL") as MealDetail
        meal = intent.getStringExtra(TypeMealActivity.MEAL_ID).toString()


        Toast.makeText(this, mealDetail?.strMeal, Toast.LENGTH_SHORT).show()
    }

    private fun setupView() {
        Glide.with(this).load(mealDetail?.strMealThumb).into(img_meal_detail)
        collapsing_toolbar.title = mealDetail?.strMeal
        collapsing_toolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        tv_categoryInfo.text = mealDetail?.strCategory
        tv_areaInfo.text = mealDetail?.strArea
        tv_content.text = mealDetail?.strInstructions


        img_youtube.setOnClickListener {
            Toast.makeText(this, "${mealDetail?.strYoutube}", Toast.LENGTH_SHORT).show()
            mealDetail?.strYoutube?.let {
                watchYoutubeVideo(it)
            }
        }

        img_back.setOnClickListener { onBackPressed() }
    }

    private fun watchYoutubeVideo(youtubeLink: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(youtubeLink)
            setPackage("com.google.android.youtube")
        }
        startActivity(intent)
    }





}
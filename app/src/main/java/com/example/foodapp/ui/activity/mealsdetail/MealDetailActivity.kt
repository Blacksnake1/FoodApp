package com.example.foodapp.ui.activity.mealsdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.data.MealDetail
import com.example.foodapp.ui.activity.meal.TypeMealActivity
import com.example.foodapp.ui.activity.meal.TypeMealActivity.Companion.FROM_CATEGORY
import com.example.foodapp.ui.fragment.favorite.FavoriteFragment
import com.example.foodapp.ui.fragment.favorite.FavoriteFragment.Companion.FROM_FAVORITE
import com.example.foodapp.ui.fragment.home.HomeFragment.Companion.RANDOM_MEAL
import kotlinx.android.synthetic.main.activity_meal_detail.*


class MealDetailActivity : AppCompatActivity() {
    private val viewmodel by lazy {
        ViewModelProvider(this)[MealDetailVM::class.java]
    }
    var mealDetail: MealDetail? = null

    var mealID: String? = null
    var favoriteID: String? = null
    var randomId: String? = null

    private var isFavorite = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_detail)
        initData()
        setupView()
        setupObserver()


    }

    private fun setupObserver() {
        viewmodel.mealDetailLiveData.observe(this) {
            mealDetail = it.meals.getOrNull(0)
            mealDetail?.let {
                updateData()
            }
        }
    }

    private fun initData() {
        randomId = intent.getStringExtra(RANDOM_MEAL)

        mealID = intent.getStringExtra(TypeMealActivity.TYPE_MEAL_ID)

        favoriteID = intent.getStringExtra(FavoriteFragment.FAVORITE_ID)

        when {
            mealID != null -> viewmodel.getMealDetail(mealID!!)

            favoriteID != null -> viewmodel.getMealDetail(favoriteID!!)

            randomId != null -> viewmodel.getMealDetail(randomId!!)

        }
        updateData()
    }

    private fun setupView() {
        img_youtube.setOnClickListener {
            Toast.makeText(this, "${mealDetail?.strYoutube}", Toast.LENGTH_SHORT).show()
            mealDetail?.strYoutube?.let {
                watchYoutubeVideo(it)
            }
        }

        img_back.setOnClickListener { onBackPressed() }

        fb_favorite.setOnClickListener {
            isFavorite = !isFavorite
            mealDetail?.let { it1 -> viewmodel.handleIsFavorite(isFavorite, it1) }
            if (isFavorite) {
                Toast.makeText(this, "Favorite Saved", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Favorite Unsave", Toast.LENGTH_SHORT).show()
            }
            updateUIButtonFavorite()
        }

    }

    private fun updateData() {
        updateUIButtonFavorite()

        Glide.with(this).load(mealDetail?.strMealThumb).into(img_meal_detail)
        collapsing_toolbar.title = mealDetail?.strMeal
        collapsing_toolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        tv_categoryInfo.text = mealDetail?.strCategory
        tv_areaInfo.text = mealDetail?.strArea
        tv_content.text = mealDetail?.strInstructions
    }


    private fun watchYoutubeVideo(youtubeLink: String) {
        /** Cách này dùng để mở link bằng trình duyệt điện thoại.
         * val intent = Intent(Intent.ACTION_VIEW,Uri.parse(youtubeLink))
         * startActivity(intent)
         */

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setPackage("com.google.android.youtube")
            data = Uri.parse(youtubeLink)
        }
        startActivity(intent)
    }

    private fun updateUIButtonFavorite() {
        if (isFavorite) {
            fb_favorite.setImageResource(R.drawable.ic_saved)
        } else {
            fb_favorite.setImageResource(R.drawable.ic_baseline_save_24)
        }
    }
}
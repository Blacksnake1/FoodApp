package com.example.foodapp.ui.activity.meal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapp.R
import com.example.foodapp.data.data.FilterCategoryModel
import com.example.foodapp.ui.activity.mealsdetail.MealDetailActivity
import com.example.foodapp.ui.fragment.home.HomeFragment
import kotlinx.android.synthetic.main.activity_meal_by_category.*

class TypeMealActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this)[TypeMealVM::class.java]
    }
    var listMeal = mutableListOf<FilterCategoryModel>()
    var adapterMeal : TypeMealAdapter? = null
    var type = ""
    companion object{
        const val TYPE_MEAL_ID = "mealid"
        const val FROM_CATEGORY = "category"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_by_category)
        setupUI()
        setupObserver()
        setupEvent()

    }

    private fun setupUI() {
        type = intent.getStringExtra(HomeFragment.CATEGORY_NAME).toString()
        setupRcvMeal()
        viewModel.getTypeMeal(type)


    }

    private fun setupObserver() {
        viewModel.typeMealLiveData.observe(this){
            it.meals?.let {
                listMeal.addAll(it)
                adapterMeal?.notifyDataSetChanged()
            }

            Log.d("dddd", "${it.meals}")
        }

    }

    private fun setupEvent() {
        toolbar.setOnClickListener {
            onBackPressed()
        }
    }
    private fun setupRcvMeal() {
        adapterMeal = TypeMealAdapter(this,listMeal,::onClickItemMeal)
        rcv_meal_by_category.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = adapterMeal
        }
    }

    private fun onClickItemMeal(filterCategoryModel: FilterCategoryModel) {
        Toast.makeText(this,filterCategoryModel.strMeal,Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MealDetailActivity::class.java)
        intent.putExtra(TYPE_MEAL_ID,filterCategoryModel.idMeal)
        intent.putExtra(FROM_CATEGORY , true)
        startActivity(intent)

    }

}
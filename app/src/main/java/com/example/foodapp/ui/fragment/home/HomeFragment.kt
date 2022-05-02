package com.example.foodapp.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.model.CategoryModel
import com.example.foodapp.data.model.MealDetail
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.ui.activity.MealDetailActivity
import kotlinx.android.synthetic.main.fragment_home.*
import com.example.foodapp.ui.fragment.home.HomeFragmentVM



class HomeFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(this)[HomeFragmentVM::class.java]
    }

    var listRandomMeal = ArrayList<MealDetail>()
    var adapterCategory : HomeAdapter? = null
    var listCategory = ArrayList<CategoryModel>()



private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentHomeBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupObsever()
        setupEvent()


    }

    private fun setupEvent() {
        viewModel.getRandomMeal()
        viewModel.getCatelog()


    }

    fun setupUi() {
        actionViewFlipper()
        setupRcvCategory()
    }

    private fun setupObsever() {
        viewModel.randomMealLiveData.observe(viewLifecycleOwner,{
            listRandomMeal.addAll(it.meals)
            Log.d("-=-=-", "id= $listRandomMeal", )
            for (i in 0 until listRandomMeal.size) {
                var imageView = ImageView(requireContext())
                Glide.with(requireContext()).load(listRandomMeal[i].strMealThumb).into(imageView)
                imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
                vf_random_meal.addView(imageView) }

        })
        viewModel.categoryLiveData.observe(viewLifecycleOwner,{
            listCategory.addAll(it.categoryModels)
            adapterCategory?.notifyDataSetChanged()

        })


    }





    private fun setupRcvCategory() {
        adapterCategory = HomeAdapter(requireContext(), listCategory,::onClickItemCategory )
        rcv_categories.layoutManager = GridLayoutManager(requireContext(),3)
        rcv_categories.adapter = adapterCategory


    }

    private fun actionViewFlipper() {
        vf_random_meal.flipInterval = 5000
        vf_random_meal.isAutoStart = true
        val slide_in = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_right)
        val slide_out = AnimationUtils.loadAnimation(requireContext(), R.anim.silde_out_right)
        vf_random_meal.inAnimation = slide_in
        vf_random_meal.outAnimation = slide_out

    }
    fun onClickItemCategory(categoryModel: CategoryModel) {
        Toast.makeText(requireContext(),categoryModel.strCategory,Toast.LENGTH_SHORT).show()
        var intent = Intent(requireContext(),MealDetailActivity::class.java)
            intent.putExtra("MealDetail", categoryModel)
            startActivity(intent)



    }

}



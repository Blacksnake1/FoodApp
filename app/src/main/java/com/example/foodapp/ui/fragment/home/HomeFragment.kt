package com.example.foodapp.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.pojo.CategoryModel
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.ui.activity.ActivityListener
import com.example.foodapp.ui.activity.HomeActivity
import com.example.foodapp.ui.activity.MealDetailActivity


class HomeFragment : Fragment(), ActivityListener {
    private val viewModel by lazy {
        ViewModelProvider(this)[HomeFragmentVM::class.java]
    }

    private lateinit var adapterCategory: HomeAdapter
    var listCategory = ArrayList<CategoryModel>()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(".....>", "onViewCreated")
        setupUI()
        setupObserver()
        setupEvent()
    }

    private fun setupUI() {
        actionViewFlipper()
        setupRcvCategory()
        (requireActivity() as HomeActivity).setActivityListener(this)
    }

    private fun setupObserver() {
        viewModel.randomMealLiveData.observe(viewLifecycleOwner) {
            if (binding.vfRandomMeal.isFlipping){
                binding.vfRandomMeal.stopFlipping()
            }

            it.meals.forEach { meal ->
                val imageView = ImageView(requireContext()).apply {
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    setOnClickListener {
                        val intent = Intent(requireActivity(),MealDetailActivity::class.java).apply {
                            putExtra("a",meal)
                        }
                        requireActivity().startActivity(intent)
                    }
                }
                Glide.with(requireContext()).load(meal.strMealThumb).into(imageView)
                binding.vfRandomMeal.addView(imageView)
                Log.e(".....>", "id ${meal.idMeal} ")
            }

            binding.vfRandomMeal.startFlipping()
        }

        viewModel.categoryLiveData.observe(viewLifecycleOwner) {
            Log.e(".....>", "${it.categories.size}")
            listCategory.addAll(it.categories)
            adapterCategory?.notifyDataSetChanged()
        }
    }

    private fun setupEvent() {
        viewModel.getCategory()
        viewModel.getRandomMeal()
    }

    private fun actionViewFlipper() {
        val slideIn = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_right)
        val slideOut = AnimationUtils.loadAnimation(requireContext(), R.anim.silde_out_right)

        binding.vfRandomMeal.apply {
            flipInterval = 5000
            inAnimation = slideIn
            outAnimation = slideOut
        }
    }

    private fun setupRcvCategory() {
        adapterCategory = HomeAdapter(requireContext(), listCategory, ::onClickItemCategory)
        binding.rcvCategories.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = adapterCategory
        }
    }

    private fun onClickItemCategory(categoryModel: CategoryModel) {
        Toast.makeText(requireContext(), categoryModel.strCategory, Toast.LENGTH_SHORT).show()

    }

    override fun onBottomTabChange(menuItem: MenuItem) {
        if (menuItem.itemId == R.id.homeFragment ){
            viewModel.getRandomMeal()
        }
    }
}



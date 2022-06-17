package com.example.foodapp.ui.fragment.categories

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapp.data.model.CategoryModel
import com.example.foodapp.databinding.FragmentCategoriesBinding
import com.example.foodapp.ui.activity.meal.TypeMealActivity
import com.example.foodapp.ui.fragment.home.HomeAdapter
import com.example.foodapp.ui.fragment.home.HomeVM

class CategoriesFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(this)[HomeVM::class.java]
    }
    private lateinit var categoriesAdapter: HomeAdapter
    var categoryList = mutableListOf<CategoryModel>()

    private lateinit var binding: FragmentCategoriesBinding

    companion object{
        const val CATEGORY_NAME = "category name"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater,container,false)
     return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupUi()
//        setupObserver()
//        setupEvent()


    }

    private fun setupUi() {
//        setupRcv()
    }



    private fun setupObserver() {
        viewModel.categoryLiveData.observe(viewLifecycleOwner){

            categoryList.addAll(it.categories)
            categoriesAdapter.notifyDataSetChanged()
        }


    }

    private fun setupEvent() {
     viewModel.getCategory()
    }

    private fun setupRcv() {
//        categoriesAdapter = HomeAdapter(requireContext(),categoryList,::onClickItem)
//        binding.rcvFragmentCategories .apply {
//          layoutManager  = GridLayoutManager(requireContext(),2)
//            adapter = categoriesAdapter
//        }

    }

    private fun onClickItem(categoryModel: CategoryModel) {
        val intent = Intent(requireContext(),TypeMealActivity::class.java)
        intent.putExtra(CATEGORY_NAME,categoryModel.strCategory)
        startActivity(intent)

    }

}
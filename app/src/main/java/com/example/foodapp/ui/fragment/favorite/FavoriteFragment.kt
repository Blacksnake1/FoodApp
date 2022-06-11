package com.example.foodapp.ui.fragment.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapp.R
import com.example.foodapp.data.data.MealDetail
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(this)[ FavoriteVM::class.java]
    }
    private lateinit var favoriteAdapter : FavoriteAdapter
    var listFavorite = ArrayList<MealDetail>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupEvent()
        setupObsever()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavorite()
    }

    private fun setupUI() {
        setupRcv()

    }
    private fun setupEvent() {

    }

    private fun setupObsever() {
        viewModel.mealList.observe(viewLifecycleOwner){
            listFavorite.clear()
            listFavorite.addAll(it)
            favoriteAdapter?.notifyDataSetChanged()
        }


    }


    private fun setupRcv() {
        favoriteAdapter = FavoriteAdapter(requireContext(), listFavorite,::onClickitem)
        fav_rec_view. apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            adapter = favoriteAdapter
        }
    }

    private fun onClickitem(mealDetail: MealDetail) {

    }


}
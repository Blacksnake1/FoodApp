package com.example.foodapp.ui.fragment.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.foodapp.R
import com.example.foodapp.ui.activity.ActivityListener
import com.example.foodapp.ui.fragment.home.HomeVM

class CategoriesFragment : Fragment(), ActivityListener {
    private val viewModel by lazy {
        ViewModelProvider(this)[HomeVM::class.java]

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onBottomTabChange(menuItem: MenuItem) {

    }

}
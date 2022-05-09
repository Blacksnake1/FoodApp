package com.example.foodapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.foodapp.R
import com.example.foodapp.ui.fragment.categories.CategoriesFragment
import com.example.foodapp.ui.fragment.favorite.FavoriteFragment
import com.example.foodapp.ui.fragment.home.HomeFragment
import com.example.foodapp.ui.fragment.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    private val fragmentCategory = CategoriesFragment()
    private val fragmentHome = HomeFragment()
    private val fragmentFavorite = FavoriteFragment()
    private val fragmentProfile = ProfileFragment()
    private lateinit var bottomNavigation : BottomNavigationView
    private var activeFragment: Fragment = fragmentHome
    lateinit var onActivityListener : ActivityListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigation = findViewById(R.id.btm_nav)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        fragmentManager.beginTransaction().apply {
            add(R.id.container, fragmentCategory,"Favorite").hide(fragmentCategory)
            add(R.id.container, fragmentFavorite, "Favorite").hide(fragmentFavorite)
            add(R.id.container, fragmentHome, "Home")
        }.commit()


        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            onActivityListener.onBottomTabChange(menuItem)
            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(fragmentHome).commit()
                    activeFragment = fragmentHome
                    true
                }

                R.id.favoriteFragment -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(fragmentFavorite).commit()
                    activeFragment = fragmentFavorite
                    true
                }

                R.id.categoriesFragment -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(fragmentCategory).commit()
                    activeFragment = fragmentCategory
                    true
                }

                R.id.profileFragment-> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(fragmentProfile).commit()
                    activeFragment = fragmentProfile
                    true
                }

                else -> false
            }
        }
    }

    fun setActivityListener(activityListener: ActivityListener){
        this.onActivityListener = activityListener
    }
}
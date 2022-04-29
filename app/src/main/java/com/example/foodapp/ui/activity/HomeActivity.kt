package com.example.foodapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.foodapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.btm_nav)
        val navControler = Navigation.findNavController(this, R.id.host_fargment)

        NavigationUI.setupWithNavController(bottomNavigation,navControler)
    }
}
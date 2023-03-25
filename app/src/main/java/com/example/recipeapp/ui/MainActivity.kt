package com.example.recipeapp.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ActivityMainBinding
import com.example.recipeapp.util.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.inflationx.viewpump.ViewPumpContextWrapper

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    //binding
    private lateinit var binding: ActivityMainBinding
    //other
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setup nav host
        navHostFragment = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
    }

    override fun onNavigateUp(): Boolean {
        return navHostFragment.navController.navigateUp() || super.onNavigateUp()
    }

}
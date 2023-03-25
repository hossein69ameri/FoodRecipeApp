package com.example.recipeapp.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
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
        //setup btm nav
        binding.mainBottomNav.setupWithNavController(navHostFragment.navController)
        binding.mainBottomNav.background = null
        //gone btm nav
        navHostFragment.navController.addOnDestinationChangedListener{_,destination,_ ->
            when(destination.id){
                R.id.splashFragment -> visibilityBottomNav(false)
                R.id.registerFragment -> visibilityBottomNav(false)
                else -> visibilityBottomNav(true)
            }
        }
    }

    private fun visibilityBottomNav(isShown : Boolean){
        binding.apply {
            if (isShown){
                mainBottomAppbar.isVisible = true
                mainFabMenu.isVisible = true
            }else {
                mainBottomAppbar.isVisible = false
                mainFabMenu.isVisible = false
            }
        }
    }
    override fun onNavigateUp(): Boolean {
        return navHostFragment.navController.navigateUp() || super.onNavigateUp()
    }

}
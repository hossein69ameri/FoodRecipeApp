package com.example.recipeapp.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.recipeapp.BuildConfig
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentSplashBinding
import com.example.recipeapp.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {
    //binding
    private lateinit var binding: FragmentSplashBinding
    //other
    private val viewModel : RegisterViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            //load background img
            bgImg.load(R.drawable.bg_splash)
            //app version
            versionTxt.text = "${getString(R.string.version)} : ${BuildConfig.VERSION_NAME}"
        }
        //auto navigate
        lifecycleScope.launchWhenCreated {
            delay(2500)
            //check
            viewModel.readRegisterData.asLiveData().observe(viewLifecycleOwner) {
                findNavController().popBackStack(R.id.splashFragment,true)
                if (it.username.isNotEmpty()) {
                   //go main
                   findNavController().navigate(R.id.action_to_recipeFragment)
                } else {
                   //go register
                   findNavController().navigate(R.id.action_to_registerFragment)
                }
            }
        }
    }

}
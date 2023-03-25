package com.example.recipeapp.ui.lucky

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentLuckyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LuckyFragment : Fragment() {
    private lateinit var binding: FragmentLuckyBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLuckyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
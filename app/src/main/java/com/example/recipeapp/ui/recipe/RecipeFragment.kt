package com.example.recipeapp.ui.recipe

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.recipeapp.R
import com.example.recipeapp.adapter.PopularAdapter
import com.example.recipeapp.databinding.FragmentRecipeBinding
import com.example.recipeapp.model.ResponseRecipes
import com.example.recipeapp.util.NetworkRequest
import com.example.recipeapp.util.setupRecyclerView
import com.example.recipeapp.util.showSnackBar
import com.example.recipeapp.viewmodel.RecipeViewModel
import com.example.recipeapp.viewmodel.RegisterViewModel
import com.todkars.shimmer.ShimmerRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class RecipeFragment : Fragment() {
    private lateinit var binding: FragmentRecipeBinding
    private val registerViewModel: RegisterViewModel by viewModels()
    private val viewModel: RecipeViewModel by viewModels()
    @Inject lateinit var popularAdapter: PopularAdapter
    private var autoScroll = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRecipeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //show username
        lifecycleScope.launchWhenCreated { showUsername() }
        //call api
        viewModel.callPopularApi(viewModel.popularQueries())
        //load data
        loadPopularData()
    }


    private fun loadPopularData() {
        viewModel.popularData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkRequest.Loading -> {
                    setupLoading(true, binding.popularList)
                }
                is NetworkRequest.Success -> {
                    setupLoading(false, binding.popularList)
                    response.data?.let { data ->
                        if (data.results!!.isNotEmpty()) {
                            popularAdapter.setData(data.results)
                            initPopularRecycler()
                            autoScrollPopular(data.results)
                        }
                    }
                }
                is NetworkRequest.Error -> {
                    setupLoading(false, binding.popularList)
                    binding.root.showSnackBar(response.message!!)
                }
            }
        }
    }

    private fun setupLoading(isShownLoading: Boolean, shimmer: ShimmerRecyclerView) {
        shimmer.apply {
            if (isShownLoading) showShimmer() else hideShimmer()
        }
    }

    private fun initPopularRecycler() {
        val snapHelper = LinearSnapHelper()
        binding.popularList.setupRecyclerView(LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false), popularAdapter)
        //snap
        snapHelper.attachToRecyclerView(binding.popularList)
        //click
        popularAdapter.setOnItemClickListener {
            //
        }
    }

    private fun autoScrollPopular(list: List<ResponseRecipes.Result>) {
        lifecycleScope.launchWhenCreated {
            repeat(100) {
                delay(2000)
                if (autoScroll < list.size) {
                    autoScroll += 1
                } else {
                    autoScroll = 0
                }
                binding.popularList.smoothScrollToPosition(autoScroll)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private suspend fun showUsername() {
        registerViewModel.readRegisterData.collect {
            binding.usernameTxt.text =
                "${getString(R.string.hello)},${it.username} ${getEmojiByUnicode()}"
        }
    }

    private fun getEmojiByUnicode(): String {
        return String(Character.toChars(0x1f44b))
    }

}
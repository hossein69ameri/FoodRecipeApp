package com.example.recipeapp.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentRegisterBinding
import com.example.recipeapp.model.BodyRegister
import com.example.recipeapp.util.*
import com.example.recipeapp.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    //binding
    private lateinit var binding: FragmentRegisterBinding
    //other
    @Inject lateinit var body: BodyRegister
    @Inject lateinit var networkChecker: NetworkChecker
    private val viewModel: RegisterViewModel by viewModels()
    private var email = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            //logo
            coverImg.load(R.drawable.register_logo)
            //check @ Email
            emailEdt.addTextChangedListener {
                if (it.toString().contains("@")) {
                    email = it.toString()
                    emailTxtLay.error = ""
                } else {
                    emailTxtLay.error = getString(R.string.emailNotValid)
                }
            }
            //Click
            submitBtn.setOnClickListener {
                val firstname = nameEdt.text.toString()
                val lastName = lastNameEdt.text.toString()
                val username = usernameEdt.text.toString()
                //Body
                body.email = email
                body.firstName = firstname
                body.lastName = lastName
                body.username = username
                //Check network
                lifecycleScope.launchWhenStarted {
                    networkChecker.checkNetworkAvailability().collect { state ->
                        if (state) {
                            //Call api
                            viewModel.callRegisterApi(Constants.SITE_API_KEY, body)
                        } else {
                            //no internet
                            root.showSnackBar(getString(R.string.checkConnection))
                        }
                    }
                }
            }
            //Load data
            loadRegisterData()
        }
    }
    private fun loadRegisterData() {
        viewModel.registerData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkRequest.Loading -> {}
                is NetworkRequest.Success -> {
                    response.data?.let { data ->
                        viewModel.saveRegisterData(data.username.toString(),data.hash.toString())
                        findNavController().popBackStack(R.id.registerFragment, true)
                        findNavController().navigate(R.id.action_to_recipeFragment)
                    }
                }
                is NetworkRequest.Error -> {
                    binding.root.showSnackBar(response.message!!)
                }
            }
        }
    }
}
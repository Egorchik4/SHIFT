package com.example.shift.screens.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.shift.R
import com.example.shift.constants.Constants
import com.example.shift.databinding.FragmentMainBinding
import com.example.shift.usecases.ShowAlertDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    private val viewModelMain: MainViewModel by viewModels()
    @Inject lateinit var showAlertDialog: ShowAlertDialog


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener(Constants.NAME_OF_USER,viewLifecycleOwner){ _, data->
            val text: String? = data.getString(Constants.USER)
            viewModelMain.saveName(text)
        }

        binding.buttonWelcome.setOnClickListener {
            showAlertDialog.showDialog(viewModelMain.name!!, requireContext())
        }


    }

}
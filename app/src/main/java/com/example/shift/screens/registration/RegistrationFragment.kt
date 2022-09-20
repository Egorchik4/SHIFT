package com.example.shift.screens.registration


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shift.R
import com.example.shift.constants.Constants
import com.example.shift.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    lateinit var binding: FragmentRegistrationBinding
    private val viewModelRegistration: RegistrationViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegistrationBinding.inflate(inflater)
        val user: String? = viewModelRegistration.userRegistration.value
        if(user != null){
            saveTextToBundle(user)
            findNavController().navigate(R.id.action_registrationFragment_to_mainFragment)
            viewModelRegistration.clearUserLiveData()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkEmpty()

        viewModelRegistration.correctName.observe(viewLifecycleOwner){
            when (viewModelRegistration.correctName.value) {
                false -> {
                    binding.textViewName.backgroundTintList = resources.getColorStateList(R.color.text_red)
                }else -> {
                    binding.textViewName.backgroundTintList = resources.getColorStateList(R.color.text_blue)
                }
            }
        }


        viewModelRegistration.correctSecondName.observe(viewLifecycleOwner){
            when (viewModelRegistration.correctSecondName.value){
                false -> {
                    binding.textViewSecondName.backgroundTintList = resources.getColorStateList(R.color.text_red)
                }else -> {
                    binding.textViewSecondName.backgroundTintList = resources.getColorStateList(R.color.text_blue)
                }
            }
        }

        viewModelRegistration.correctDateOfBirthday.observe(viewLifecycleOwner){
            when (viewModelRegistration.correctDateOfBirthday.value){
                false ->{
                    binding.editTextDate.backgroundTintList = resources.getColorStateList(R.color.text_red)
                }else->{
                    binding.editTextDate.backgroundTintList = resources.getColorStateList(R.color.text_blue)
                }
            }
        }

        viewModelRegistration.correctPassword.observe(viewLifecycleOwner){
            when (viewModelRegistration.correctPassword.value){
                false ->{
                    binding.editTextPassword.backgroundTintList = resources.getColorStateList(R.color.text_red)
                }else ->{
                    binding.editTextPassword.backgroundTintList = resources.getColorStateList(R.color.text_blue)
                }
            }
        }
        viewModelRegistration.matchingPassword.observe(viewLifecycleOwner){
            when (viewModelRegistration.matchingPassword.value){
                false ->{
                    binding.editTextCheckPassword.backgroundTintList = resources.getColorStateList(R.color.text_red)
                }else ->{
                    binding.editTextCheckPassword.backgroundTintList = resources.getColorStateList(R.color.text_blue)
                }
            }
        }

        viewModelRegistration.enableRegistrationButton.observe(viewLifecycleOwner){
            binding.buttonRegistration.isEnabled = viewModelRegistration.enableRegistrationButton.value!!
        }


        binding.textViewName.addTextChangedListener {
            checkEmpty()
            viewModelRegistration.checkValidationName(it.toString())
        }

        binding.textViewSecondName.addTextChangedListener {
            checkEmpty()
            viewModelRegistration.checkValidationSecondName(it.toString())
        }

        binding.editTextDate.addTextChangedListener {
            checkEmpty()
            viewModelRegistration.checkValidationDateOfBirthday(it.toString())
        }

        binding.editTextPassword.addTextChangedListener {
            checkEmpty()
            viewModelRegistration.checkValidationPassword(it.toString())
            viewModelRegistration.checkMatchingPassword(it.toString(), binding.editTextCheckPassword.text.toString())
        }

        binding.editTextCheckPassword.addTextChangedListener {
            checkEmpty()
            viewModelRegistration.checkMatchingPassword(binding.editTextPassword.text.toString(), it.toString())
        }


        
        binding.buttonRegistration.setOnClickListener {
            viewModelRegistration.checkAllValidation(
                binding.textViewName.text.toString(),
                binding.textViewSecondName.text.toString(),
                binding.editTextDate.text.toString(),
                binding.editTextPassword.text.toString(),
                binding.editTextCheckPassword.text.toString()
            )
            if(viewModelRegistration.checkValidation()){
                saveTextToBundle(binding.textViewName.text.toString())
                viewModelRegistration.saveUserNameToStorage(binding.textViewName.text.toString())
                findNavController().navigate(R.id.action_registrationFragment_to_mainFragment)
                viewModelRegistration.clearUserLiveData()
            }
        }
    }


    private fun saveTextToBundle(text: String){
        val bundle = Bundle()
        bundle.putString(Constants.USER,text)
        parentFragmentManager.setFragmentResult(Constants.NAME_OF_USER,bundle)
    }

    private fun checkEmpty(){
        viewModelRegistration.checkEnableRegistrationButton(
            binding.textViewName.text.toString(),
            binding.textViewSecondName.text.toString(),
            binding.editTextDate.text.toString(),
            binding.editTextPassword.text.toString(),
            binding.editTextCheckPassword.text.toString()
        )
    }


}
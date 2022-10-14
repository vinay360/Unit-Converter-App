package com.example.unitconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import com.example.unitconverter.databinding.FragmentInputBinding
import com.example.unitconverter.mymodel.MyViewModel
import com.google.android.material.snackbar.Snackbar

class InputFragment : Fragment() {

    lateinit var binding: FragmentInputBinding
    val viewModel: MyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_input, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.inputCelsius.doAfterTextChanged {
            if (binding.inputCelsius.hasFocus()) {
                viewModel.celsius.value = binding.inputCelsius.text?.toString() ?: ""
                if (viewModel.celsius.value!!.isEmpty()) {
                    viewModel.fahrenheit.value = ""
                } else {
                    viewModel.fahrenheit.value =
                        toFahrenheit(binding.inputCelsius.text?.toString() ?: "")
                }
            }
        }
        binding.inputFahrenheit.doAfterTextChanged {
            if (binding.inputFahrenheit.hasFocus()) {
                viewModel.fahrenheit.value = binding.inputFahrenheit.text?.toString() ?: ""
                if (viewModel.fahrenheit.value!!.isEmpty()) {
                    viewModel.celsius.value = ""
                } else {
                    viewModel.celsius.value =
                        toCelsius(binding.inputFahrenheit.text?.toString() ?: "")
                }
            }
        }
        binding.button.setOnClickListener {
            val action = InputFragmentDirections.actionInputFragmentToThermometerFragment()
            view.findNavController().navigate(action)
        }
    }

    private fun toFahrenheit(inputCelsius: String): String {
        val v: Float = when (inputCelsius.length) {
            0 -> 0F
            else -> inputCelsius.toFloat()
        }
        return ((v) * 9 / 5 + 32).toString()
    }

    private fun toCelsius(inputFahrenheit: String): String {
        val v: Float = when (inputFahrenheit.length) {
            0 -> 0F
            else -> inputFahrenheit.toFloat()
        }
        return ((v - 32) * 5 / 9).toString()
    }
}
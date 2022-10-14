package com.example.unitconverter

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.unitconverter.databinding.FragmentThermometerBinding
import com.example.unitconverter.mymodel.MyViewModel

class ThermometerFragment : Fragment() {

    lateinit var binding: FragmentThermometerBinding
    val viewModel : MyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_thermometer, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val temp = viewModel.celsius.value!!
        if(temp.isNotEmpty())
        binding.meter.setValueAndStartAnim(viewModel.celsius.value!!.toFloat())
        else
            binding.meter.setCurValue(0F)
    }
}
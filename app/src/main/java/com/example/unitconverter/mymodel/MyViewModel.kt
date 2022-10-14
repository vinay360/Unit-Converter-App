package com.example.unitconverter.mymodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    var celsius  = MutableLiveData<String>()
    var fahrenheit = MutableLiveData<String>()
}
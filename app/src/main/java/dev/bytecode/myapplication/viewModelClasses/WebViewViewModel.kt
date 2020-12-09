package dev.bytecode.myapplication.viewModelClasses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebViewViewModel : ViewModel() {

    val isLoading = MutableLiveData(true)


}
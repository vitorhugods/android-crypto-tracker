package xyz.schwaab.crypto.app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import xyz.schwaab.crypto.base.presentation.BaseViewModel

open class MainViewModel : BaseViewModel() {
    private val _mainTitle = MutableLiveData("Bitcoin Price")
    val mainTitle: LiveData<String> = _mainTitle
}
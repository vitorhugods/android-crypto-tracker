package xyz.schwaab.crypto.app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import xyz.schwaab.crypto.base.BaseViewModel

open class MainViewModel : BaseViewModel() {
    private val _mainTitle = MutableLiveData("Title")
    val mainTitle: LiveData<String> = _mainTitle
}
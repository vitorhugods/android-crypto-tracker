package xyz.schwaab.template.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import xyz.schwaab.template.base.BaseViewModel

open class MainViewModel : BaseViewModel() {
    private val _mainTitle = MutableLiveData("Title")
    val mainTitle: LiveData<String> = _mainTitle
}
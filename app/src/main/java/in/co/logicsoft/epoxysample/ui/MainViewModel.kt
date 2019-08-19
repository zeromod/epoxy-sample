package `in`.co.logicsoft.epoxysample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _data = MutableLiveData<List<String>>()
    val data: LiveData<List<String>> = _data

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    fun initData() {
        _data.value = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
    }

    fun clickEvent(value: String) {
        _toastMessage.value = value
    }
}
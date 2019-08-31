package `in`.co.logicsoft.epoxysample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _item = MutableLiveData<List<String>>()
    val item: LiveData<List<String>> = _item

    private val _header = MutableLiveData<List<String>>()
    val header: LiveData<List<String>> = _header

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    fun initData() {
        _item.value = listOf(
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen",
            "Twenty"
        )
        _header.value = listOf("Header 1", "Header 2", "Header 3", "Header 4")
    }

    fun clickEvent(value: String) {
        _toastMessage.value = value
    }
}
package `in`.co.logicsoft.epoxysample.epoxy

import `in`.co.logicsoft.epoxysample.listItem
import `in`.co.logicsoft.epoxysample.ui.MainViewModel
import com.airbnb.epoxy.TypedEpoxyController

class SampleController(private val mainViewModel: MainViewModel) : TypedEpoxyController<List<String>>() {
    override fun buildModels(data: List<String>?) {
        data?.forEach {
            listItem {
                id("firstName")
                text(it)
                viewModel(mainViewModel)
            }
        }
    }

}
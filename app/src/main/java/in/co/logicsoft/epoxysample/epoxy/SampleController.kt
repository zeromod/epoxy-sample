package `in`.co.logicsoft.epoxysample.epoxy

import `in`.co.logicsoft.epoxysample.listHeader
import `in`.co.logicsoft.epoxysample.listItem
import `in`.co.logicsoft.epoxysample.ui.MainViewModel
import com.airbnb.epoxy.TypedEpoxyController

class SampleController(private val mainViewModel: MainViewModel) : TypedEpoxyController<List<String>>() {
    override fun buildModels(data: List<String>?) {
        listHeader {
            id("Header 1")
            header("Header 1")
            viewModel(mainViewModel)
        }
        data?.forEach {
            listItem {
                id("firstName")
                text(it)
                viewModel(mainViewModel)
            }
        }
        listHeader {
            id("Header 2")
            header("Header 2")
            viewModel(mainViewModel)
        }
        data?.forEach {
            listItem {
                id("firstName")
                text(it)
                viewModel(mainViewModel)
            }
        }
    }

}
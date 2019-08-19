package `in`.co.logicsoft.epoxysample.epoxy

import `in`.co.logicsoft.epoxysample.listHeader
import `in`.co.logicsoft.epoxysample.listItem
import `in`.co.logicsoft.epoxysample.ui.MainViewModel
import com.airbnb.epoxy.Typed2EpoxyController

class SampleController(private val mainViewModel: MainViewModel) : Typed2EpoxyController<List<String>, List<String>>() {
    override fun buildModels(data1: List<String>?, data2: List<String>?) {
        data2?.forEachIndexed { index, header ->
            listHeader {
                id(index)
                header(header)
                viewModel(mainViewModel)
            }
            data1?.forEachIndexed { itemIndex, item ->
                listItem {
                    id(itemIndex)
                    text(item)
                    viewModel(mainViewModel)
                }
            }
        }
    }
}
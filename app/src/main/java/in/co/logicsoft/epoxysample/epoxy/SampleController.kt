package `in`.co.logicsoft.epoxysample.epoxy

import `in`.co.logicsoft.epoxysample.listHeader
import `in`.co.logicsoft.epoxysample.listItem
import `in`.co.logicsoft.epoxysample.ui.MainViewModel
import com.airbnb.epoxy.TypedEpoxyController

@Suppress("UNCHECKED_CAST")
class SampleController(private val mainViewModel: MainViewModel) : TypedEpoxyController<List<Any>>() {
    override fun buildModels(data: List<Any>?) {
        data?.forEachIndexed { index, any ->
            when (any) {
                is List<*> -> {
                    val items = any as List<String>
                    items.forEachIndexed { itemIndex, item ->
                        listItem {
                            id(itemIndex)
                            text(item)
                            viewModel(mainViewModel)
                        }
                    }
                }
                is Int -> {
                    listHeader {
                        id(index)
                        header(any.toString())
                        viewModel(mainViewModel)
                    }
                }
            }
        }
    }
}
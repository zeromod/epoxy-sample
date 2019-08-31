package `in`.co.logicsoft.epoxysample.epoxy

import `in`.co.logicsoft.epoxysample.ChildViewBindingModel_
import `in`.co.logicsoft.epoxysample.listHeader
import `in`.co.logicsoft.epoxysample.ui.MainViewModel
import com.airbnb.epoxy.Typed2EpoxyController
import com.airbnb.epoxy.carousel

class SampleController(private val mainViewModel: MainViewModel) :
    Typed2EpoxyController<List<String>, List<String>>() {
    override fun buildModels(data1: List<String>?, data2: List<String>?) {
        data2?.forEachIndexed { index, header ->
            listHeader {
                id(index)
                header(header)
                viewModel(mainViewModel)
            }
            data1?.let {
                carousel {
                    id("carousal $index")
                    numViewsToShowOnScreen(1.2f)
                    withModelsFrom(it) {
                        ChildViewBindingModel_()
                            .id(it)
                            .name(it)
                    }
                }
            }
        }
    }
}
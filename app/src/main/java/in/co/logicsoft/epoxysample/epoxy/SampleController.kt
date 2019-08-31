package `in`.co.logicsoft.epoxysample.epoxy

import `in`.co.logicsoft.epoxysample.ChildViewBindingModel_
import `in`.co.logicsoft.epoxysample.listHeader
import `in`.co.logicsoft.epoxysample.ui.MainViewModel
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.Typed2EpoxyController
import java.util.*

class SampleController(private val mainViewModel: MainViewModel) :
    Typed2EpoxyController<List<String>, List<String>>() {
    override fun buildModels(data1: List<String>?, data2: List<String>?) {
        data2?.forEachIndexed { index, header ->
            val models = ArrayList<EpoxyModel<*>>()
            listHeader {
                id(index)
                header(header)
                viewModel(mainViewModel)
            }
            data1?.forEachIndexed { itemIndex, item ->
                models.add(
                    ChildViewBindingModel_()
                        .id(itemIndex)
                        .name(item)
                )
            }
            CarouselModel_()
                .id("carousal $index")
                .models(models)
                .addTo(this)
        }
    }
}
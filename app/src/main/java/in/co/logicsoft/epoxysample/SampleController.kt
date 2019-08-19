package `in`.co.logicsoft.epoxysample

import com.airbnb.epoxy.TypedEpoxyController

class SampleController : TypedEpoxyController<List<String>>() {
    override fun buildModels(data: List<String>?) {
        data?.forEach {
            listItem {
                id("firstName")
                text(it)
            }
        }
    }

}
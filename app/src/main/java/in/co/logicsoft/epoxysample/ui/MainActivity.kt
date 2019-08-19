package `in`.co.logicsoft.epoxysample.ui

import `in`.co.logicsoft.epoxysample.ListHeaderBindingModel_
import `in`.co.logicsoft.epoxysample.R
import `in`.co.logicsoft.epoxysample.epoxy.SampleController
import `in`.co.logicsoft.epoxysample.util.StickyHeaders
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var controller: SampleController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        setupRecycler()
        subscribeUI()
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.initData()

        controller = SampleController(viewModel)
    }

    private fun setupRecycler() {
        sample_list.adapter = controller.adapter
        sample_list.addItemDecoration(StickyHeaders(controller, ListHeaderBindingModel_::class.java))
    }

    private fun subscribeUI() {
        var items = emptyList<String>()
        var headers = emptyList<String>()
        viewModel.item.observe(this, Observer {
            items = it
            controller.setData(items, headers)
        })
        viewModel.header.observe(this, Observer {
            headers = it
            controller.setData(items, headers)
        })

        viewModel.toastMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }
}

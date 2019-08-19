package `in`.co.logicsoft.epoxysample.ui

import `in`.co.logicsoft.epoxysample.R
import `in`.co.logicsoft.epoxysample.epoxy.SampleController
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
    }

    private fun subscribeUI() {
        viewModel.data.observe(this, Observer {
            controller.setData(it)
        })

        viewModel.toastMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }
}

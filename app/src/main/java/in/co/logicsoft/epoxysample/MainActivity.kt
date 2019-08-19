package `in`.co.logicsoft.epoxysample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val controller = SampleController()
        controller.setData(listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"))
        sample_list.adapter = controller.adapter
    }
}

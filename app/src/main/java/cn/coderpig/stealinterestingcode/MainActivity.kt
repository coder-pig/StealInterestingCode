package cn.coderpig.stealinterestingcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.coderpig.stealinterestingcode.SlidingMisalignment.MapScrollActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.ele.uetool.UETool

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        UETool.showUETMenu()
        tv_test.setOnClickListener { fly<MapScrollActivity>() }
    }
}

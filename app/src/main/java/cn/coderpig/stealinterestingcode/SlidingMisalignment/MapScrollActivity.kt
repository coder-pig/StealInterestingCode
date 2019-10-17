package cn.coderpig.stealinterestingcode.SlidingMisalignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.coderpig.stealinterestingcode.R
import cn.coderpig.stealinterestingcode.SlidingMisalignment.adapter.LineAdapter
import cn.coderpig.stealinterestingcode.addOnScrollListener
import cn.coderpig.stealinterestingcode.trueLet
import kotlinx.android.synthetic.main.activity_map_scroll.*

/**
 * @Author: CoderPig
 * @Description:
 * @Date: Create in 下午 02:57 2019/10/17 0017
 */
class MapScrollActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_scroll)
        rv_main_homework.addOnScrollListener(
            scrolled = { rv, dx, dy ->
                run {
                    (rv.scrollState != 0).trueLet {
                        rv_back_level_bg.scrollBy(dx * 0.4.toInt(), dy)
                        rv_middle_level_bg.scrollBy(dx * 0.6.toInt(), dy)
                        rv_front_level_bg.scrollBy(dx * 0.8.toInt(), dy)
                        rv_line.scrollBy(dx , dy)
                    }
                }
            }
        )
        val mAdapter = LineAdapter(this)
        rv_line.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv_line.adapter = mAdapter
        mAdapter.updateSize(30)
    }
}
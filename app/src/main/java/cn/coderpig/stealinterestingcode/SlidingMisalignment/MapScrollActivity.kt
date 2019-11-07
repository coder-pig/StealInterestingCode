package cn.coderpig.stealinterestingcode.SlidingMisalignment

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import cn.coderpig.stealinterestingcode.R
import cn.coderpig.stealinterestingcode.SlidingMisalignment.adapter.*
import cn.coderpig.stealinterestingcode.addOnScrollListener
import cn.coderpig.stealinterestingcode.trueLet
import kotlinx.android.synthetic.main.activity_map_scroll.*
import android.view.Window.FEATURE_NO_TITLE
import cn.coderpig.stealinterestingcode.SlidingMisalignment.data.LessonStatusInfo
import cn.coderpig.stealinterestingcode.dp2px


/**
 * @Author: CoderPig
 * @Description:
 * @Date: Create in 下午 02:57 2019/10/17 0017
 */
class MapScrollActivity : AppCompatActivity() {
    private val onTouchListener = View.OnTouchListener { _, _ -> true }
    private var  mData = ArrayList<LessonStatusInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_map_scroll)
        rv_main_homework.addOnScrollListener(
            scrolled = { rv, dx, dy ->
                run {
                    (rv.scrollState != SCROLL_STATE_IDLE).trueLet {
                        rv_back_level_bg.scrollBy((dx * 0.4).toInt(), dy)
                        rv_middle_level_bg.scrollBy((dx * 0.6).toInt(), dy)
                        rv_front_level_bg.scrollBy((dx * 0.8).toInt(), dy)
                        rv_line.scrollBy(dx, dy)
                    }
                }
            },
            scrollStateChanged = { rv, newState ->
                run {
                    val lm = rv.layoutManager as LinearLayoutManager
                    (newState == SCROLL_STATE_IDLE).trueLet {
                        val findFirstVisibleItemPosition = lm.findFirstVisibleItemPosition()
                        val findLastVisibleItemPosition = lm.findLastVisibleItemPosition()
                        iv_back_today_left.visibility = if(findFirstVisibleItemPosition > 1) View.VISIBLE else View.GONE
                        iv_back_today_right.visibility = if(findLastVisibleItemPosition < 1) View.VISIBLE else View.GONE
                    }
                }
            }
        )
        iv_back_today_left.scale = 0.5f
        iv_back_today_left.playAnimation()
        iv_back_today_left.setOnClickListener { rv_main_homework.smoothScrollToPosition(1) }

        rv_back_level_bg.setOnTouchListener(onTouchListener)
        rv_front_level_bg.setOnTouchListener(onTouchListener)
        rv_middle_level_bg.setOnTouchListener(onTouchListener)
        rv_line.setOnTouchListener(onTouchListener)

        rv_back_level_bg.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv_back_level_bg.adapter = BgAdapter(this, 1)

        rv_middle_level_bg.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv_middle_level_bg.adapter = BgAdapter(this, 2)

        rv_front_level_bg.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv_front_level_bg.adapter = BgAdapter(this, 3)

        val mLineAdapter = LineAdapter(this)
        rv_line.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv_line.adapter = mLineAdapter
        mLineAdapter.updateSize(49)

        // 构造假数据：
        for(i in 1..50) {
            when(i) {
                1 -> mData.add(LessonStatusInfo(i,1, "1", "", false, 1, 0))
                2 -> mData.add(LessonStatusInfo(i,2, "", "", true, 2, 0))
                3 -> mData.add(LessonStatusInfo(i,3, "2", "", false, 3, 0))
                4 -> mData.add(LessonStatusInfo(i,4, "", "", false, 4, 0))
                5 -> mData.add(LessonStatusInfo(i,5, "", "", false, 5, 0))
                6 -> mData.add(LessonStatusInfo(i,6, "3", "", false, 6, 0))
                else -> mData.add(LessonStatusInfo(i,3, "", "", false, 2, 0))
            }
        }
        val mWorkAdapter = WorkAdapter(this)
        rv_main_homework.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv_main_homework.adapter = mWorkAdapter
        mWorkAdapter.addAll(mData)
        rv_main_homework.setPadding(0,0,0,0) // 存在第一关，去除偏移
        rv_line.setPadding(dp2px(80.0f), 0 , dp2px(80.0f), 0)

        iv_back_today_right.scale = 0.5f
        iv_back_today_right.playAnimation()
        iv_back_today_right.setOnClickListener { this.rv_main_homework.smoothScrollToPosition(1) }
    }
}
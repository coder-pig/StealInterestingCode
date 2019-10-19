package cn.coderpig.stealinterestingcode.SlidingMisalignment

import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
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


/**
 * @Author: CoderPig
 * @Description:
 * @Date: Create in 下午 02:57 2019/10/17 0017
 */
class MapScrollActivity : AppCompatActivity() {

    private val onTouchListener = View.OnTouchListener { _, _ -> true }

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
            }
        )

        rv_back_level_bg.setOnTouchListener(onTouchListener)
        rv_front_level_bg.setOnTouchListener(onTouchListener)
        rv_middle_level_bg.setOnTouchListener(onTouchListener)

        val mLineAdapter = LineAdapter(this)
        rv_line.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv_line.adapter = mLineAdapter
        mLineAdapter.updateSize(30)

        val mBackLevelBgAdapter = BackLevelBgAdapter(this)
        rv_back_level_bg.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv_back_level_bg.adapter = mBackLevelBgAdapter
        mBackLevelBgAdapter.updateSize(30)

        val mFrontLevelBgAdapter = FrontLevelBgAdapter(this)
        rv_front_level_bg.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv_front_level_bg.adapter = mFrontLevelBgAdapter
        mFrontLevelBgAdapter.updateSize(30)

        val mMiddleLevelBgAdapter = MiddleLevelBgAdapter(this)
        rv_middle_level_bg.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv_middle_level_bg.adapter = mMiddleLevelBgAdapter
        mMiddleLevelBgAdapter.updateSize(30)

        val mMainHomeWorkAdapter = MainHomeWorkAdapter(this)
        rv_main_homework.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv_main_homework.adapter = mMainHomeWorkAdapter
        mMainHomeWorkAdapter.updateSize(30)

    }
}
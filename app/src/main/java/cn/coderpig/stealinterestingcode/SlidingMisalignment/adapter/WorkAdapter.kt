package cn.coderpig.stealinterestingcode.SlidingMisalignment.adapter

import android.content.Context
import android.graphics.Color
import android.util.Range
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import cn.coderpig.stealinterestingcode.R
import cn.coderpig.stealinterestingcode.SlidingMisalignment.data.LessonStatusInfo
import cn.coderpig.stealinterestingcode.SlidingMisalignment.widget.EnTextView
import cn.coderpig.stealinterestingcode.dp2px
import cn.coderpig.stealinterestingcode.getScreenHeight
import cn.coderpig.stealinterestingcode.trueLet
import kotlinx.android.synthetic.main.item_work.view.*
import kotlinx.android.synthetic.main.item_work.view.iv_lock
import kotlinx.android.synthetic.main.item_work_end.view.*
import kotlinx.android.synthetic.main.item_work_start.view.*
import kotlinx.android.synthetic.main.item_work_start.view.layout_root

/**
 * 描述：
 *
 * @author jay on 2019-11-05 20:38
 */
class WorkAdapter (var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val COMMON_VIEW_ITEM = 0
        private const val START_VIEW_ITEM = 1
        private const val END_VIEW_ITEM = 2
    }

    private var mData = ArrayList<LessonStatusInfo>()
    private var d: Int = dp2px(80.0f)  // 显示在下方
    private var e: Int = ((getScreenHeight() - dp2px(84.0f)) - d) / 2 // 显示在上方

    fun addAll(data: ArrayList<LessonStatusInfo>) {
        this.mData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            COMMON_VIEW_ITEM -> CommonHolder(LayoutInflater.from(context).inflate(R.layout.item_work, parent, false))
            START_VIEW_ITEM -> StartHolder(LayoutInflater.from(context).inflate(R.layout.item_work_start, parent,false))
            else -> EndHolder(LayoutInflater.from(context).inflate(R.layout.item_work_end, parent, false))
        }
    }

    override fun getItemCount(): Int {
        mData.isNullOrEmpty().trueLet { return 0  }
        return mData.size + 2
    }

    override fun getItemViewType(position: Int): Int {
        (position == itemCount - 1).trueLet { return END_VIEW_ITEM }
        (position != 0).trueLet { return COMMON_VIEW_ITEM }
        return START_VIEW_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) { (holder as ViewHolder).bind(position) }

    abstract class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(position: Int)
    }

    inner class CommonHolder(view: View): ViewHolder(view) {
        val iv_lock = view.iv_lock
        val iv_current_flag = view.iv_current_flag
        val ll_unit = view.ll_unit
        val tv_unit = view.tv_unit
        val rl_course_content = view.rl_course_content
        val tv_item_pic_flag = view.tv_item_pic_flag
        val tv_item_index = view.tv_item_index
        val anim_current_day = view.anim_current_day

        override fun bind(position: Int) {
            var a = 0
            rl_course_content.tag = position
            // 控制内容是显示在上方还是下方
            (position % 2 == 0).trueLet { a = e }.elseLet { a = e + d }
            val layoutParams = RelativeLayout.LayoutParams(dp2px(84.0f), dp2px(84.0f))
            layoutParams.apply{
                addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                addRule(RelativeLayout.CENTER_HORIZONTAL)
                setMargins(0,0,0, a)
            }
            rl_course_content.layoutParams = layoutParams
            // 判断是否为第一个，相关控件隐藏
            (position == 0).trueLet {
                tv_item_index.text = ""
                tv_item_index.setBackgroundResource(R.drawable.icon_main_map_welcome)
                iv_lock.visibility = View.GONE
                iv_current_flag.visibility = View.GONE
                ll_unit.visibility = View.GONE
                anim_current_day.visibility = View.GONE
                tv_item_pic_flag.visibility = View.GONE
            }
            val data = mData[position - 1]
            // 根据课程类型与状态设置状态
            (data.lessonType in listOf(3, 4)).trueLet { tv_item_index.text = "" } elseLet { tv_item_index.text = data.lessonDay.toString() }
            when(data.lessonType) {
                2,7 -> { tv_item_index.setBackgroundResource(
                    if(data.status == 3) R.drawable.icon_main_map_super_video_unenable
                    else R.drawable.icon_main_map_super_video_enable ) }
                8 -> {
                    tv_item_index.setBackgroundResource(
                        if(data.status == 3) R.drawable.icon_main_map_super_sing_unenable
                        else R.drawable.icon_main_map_super_sing_enable) }
                3 -> {
                    tv_item_index.setBackgroundResource(
                        if(data.status == 3) R.drawable.icon_main_map_carnival_unenable
                        else R.drawable.icon_main_map_carnival_enable)
                }
                4 -> {
                    tv_item_index.setBackgroundResource(
                        if(data.status == 3) R.drawable.main_item_bg_unable
                        else R.drawable.main_item_bg)
                }
                else -> {
                    (data.status == 3)
                        .trueLet { tv_item_index.setBackgroundResource(
                                if(data.g == 2) R.drawable.icon_main_map_test_b_unenable
                                else R.drawable.icon_main_map_test_a_unenable) }
                        .elseLet {
                            tv_item_index.setBackgroundResource(
                                if(data.g == 2) R.drawable.icon_main_map_test_b_enable
                                else R.drawable.icon_main_map_test_a_enable) }
                }
            }

            tv_item_pic_flag.setTextColor(Color.parseColor(if(data.status == 3) "#BBBED5" else "#4A546D" ))
            when(data.lessonType) {
                5 -> tv_item_pic_flag.apply { visibility = View.VISIBLE; text = "绘本上" }
                6 -> tv_item_pic_flag.apply { visibility = View.VISIBLE; text = "绘本下" }
                else -> tv_item_pic_flag.visibility = View.GONE
            }

            data.unit.isNullOrBlank()
                .trueLet { ll_unit.visibility = View.GONE }
                .elseLet { ll_unit.visibility = View.VISIBLE; tv_unit.text = "Unit ${data.unit}" }

            when(data.status) {
                1 -> { iv_lock.apply { setImageResource(R.drawable.icon_main_map_course_no_finish); visibility = View.VISIBLE} }
                2 -> { iv_lock.apply { setImageResource(R.drawable.icon_main_map_course_finish); visibility = View.VISIBLE} }
                3 -> { iv_lock.apply { setImageResource(R.drawable.icon_course_change_lock); visibility = View.VISIBLE} }
                else -> { iv_lock.visibility = View.GONE }
            }
            data.e.trueLet {
                iv_current_flag.visibility = View.VISIBLE
                anim_current_day.visibility = View.VISIBLE
                anim_current_day.scale = 0.5f
                anim_current_day.playAnimation()
                return
            }

            iv_current_flag.visibility = View.GONE
            anim_current_day.visibility = View.GONE
        }
    }

    inner class StartHolder(view: View): ViewHolder(view) {
        val layout_root = view.layout_root

        override fun bind(position: Int) {
            layout_root.layoutParams = RecyclerView.LayoutParams(dp2px(160.0f), getScreenHeight())
        }
    }

    inner class EndHolder(view: View): ViewHolder(view) {
        val layout_root = view.layout_root

        override fun bind(position: Int) {
            layout_root.layoutParams = RecyclerView.LayoutParams(dp2px(160.0f), getScreenHeight())
        }
    }

}
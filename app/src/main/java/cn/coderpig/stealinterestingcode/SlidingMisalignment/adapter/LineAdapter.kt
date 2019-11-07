package cn.coderpig.stealinterestingcode.SlidingMisalignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.coderpig.stealinterestingcode.R
import cn.coderpig.stealinterestingcode.SlidingMisalignment.widget.LineView
import cn.coderpig.stealinterestingcode.trueLet
import kotlinx.android.synthetic.main.item_line.view.*

/**
 * @Author: CoderPig
 * @Description:
 * @Date: Create in 下午 11:57 2019/10/17 0017
 */
class LineAdapter(private var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mSize = 0

    fun updateSize(size: Int) {
        this.mSize = size
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_line, null, false))

    override fun getItemCount(): Int = this.mSize

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (position != 0 && position != itemCount - 1)
            .trueLet {
                (holder as ViewHolder).lv.visibility = View.VISIBLE
                holder.setIsRecyclable(false)
                holder.lv.setIsUp((position % 2 == 0))
                return
            }
        (holder as ViewHolder).lv.visibility = View.INVISIBLE
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) { var lv: LineView = view.lv }
}



package cn.coderpig.stealinterestingcode.SlidingMisalignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.coderpig.stealinterestingcode.R
import cn.coderpig.stealinterestingcode.SlidingMisalignment.widget.LineView
import cn.coderpig.stealinterestingcode.trueLet

/**
 * @Author: CoderPig
 * @Description:
 * @Date: Create in 下午 11:57 2019/10/17 0017
 */
class LineAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mSize = 0

    fun updateSize(size: Int) {
        this.mSize = size
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_line, null, false))
    }

    override fun getItemCount(): Int = this.mSize

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var lv = view.findViewById<LineView>(R.id.lv)
        fun bind(position: Int) {
            var isUp: Boolean
            (position != 0 && position != itemCount - 1)
                .trueLet {
                    lv.visibility = View.VISIBLE
                    this@ViewHolder.setIsRecyclable(false)
                    isUp = (position % 2 == 0)
                    lv.setIsUp(isUp)
                    return
                }
            lv.visibility = View.INVISIBLE
        }
    }

}
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
 * @Date: Create in 下午 05:25 2019/10/18 0018
 */
class BackLevelBgAdapter (var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mSize = 0

    fun updateSize(size: Int) {
        this.mSize = size
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_back_level_bg, parent, false))
    }

    override fun getItemCount(): Int = this.mSize

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
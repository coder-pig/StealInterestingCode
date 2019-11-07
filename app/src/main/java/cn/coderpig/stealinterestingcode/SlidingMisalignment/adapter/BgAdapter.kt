package cn.coderpig.stealinterestingcode.SlidingMisalignment.adapter

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import cn.coderpig.stealinterestingcode.R
import cn.coderpig.stealinterestingcode.getScreenHeight
import cn.coderpig.stealinterestingcode.openRawResource
import cn.coderpig.stealinterestingcode.trueLet
import java.io.IOException
import kotlin.math.roundToInt

/**
 * @Author: CoderPig
 * @Description:
 * @Date: Create in 下午 05:25 2019/10/18 0018
 */
class BgAdapter(var context: Context, type: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var bgResIds = ArrayList<Int>()
    private var screenHeight = getScreenHeight()

    init {
        val obtainTypedArray: TypedArray = when (type) {
            1 -> context.resources.obtainTypedArray(R.array.back_bg_array)
            2 -> context.resources.obtainTypedArray(R.array.middle_bg_array)
            else -> context.resources.obtainTypedArray(R.array.front_bg_array)
        }
        for (i in 0 until obtainTypedArray.length()) {
            bgResIds.add(
                obtainTypedArray.getResourceId(i, R.color.transparent)
            )
        }
        obtainTypedArray.recycle()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(ImageView(context))

    override fun getItemCount(): Int = bgResIds.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var width = getDrawableWidth(bgResIds[position], (holder as ViewHolder).iv)
        width = ((width * screenHeight) / 750.0F).roundToInt()
        (holder.iv.layoutParams == null)
            .trueLet { holder.iv.layoutParams = ViewGroup.LayoutParams(width, screenHeight) }
            .elseLet {
                (holder.iv.layoutParams.width != width)
                    .trueLet {
                        holder.iv.layoutParams.width = width
                        holder.iv.layoutParams.height = screenHeight
                }
            }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) { var iv = view as ImageView }

    private fun getDrawableWidth(resId: Int, iv: ImageView): Int {
        val openRawResource = openRawResource(resId)
        val decodeStream = BitmapFactory.decodeStream(
            openRawResource, null, BitmapFactory.Options().apply {
                inJustDecodeBounds = false
                inPreferredConfig = Bitmap.Config.RGB_565
                inSampleSize = 1 }
        )
        iv.setImageBitmap(decodeStream)
        try {
            openRawResource.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return decodeStream!!.width
    }
}



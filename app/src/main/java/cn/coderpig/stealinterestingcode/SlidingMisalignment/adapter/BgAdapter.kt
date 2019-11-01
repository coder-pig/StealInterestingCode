package cn.coderpig.stealinterestingcode.SlidingMisalignment.adapter

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import cn.coderpig.stealinterestingcode.R
import cn.coderpig.stealinterestingcode.getScreenHeight
import cn.coderpig.stealinterestingcode.openRawResource
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.IOException

/**
 * @Author: CoderPig
 * @Description:
 * @Date: Create in 下午 05:25 2019/10/18 0018
 */
class BgAdapter (var context: Context, type: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var bgResIds = ArrayList<Int>()
    private var screenHeight = getScreenHeight()

    init {
        val obtainTypedArray: TypedArray = when(type) {
            1 -> context.resources.obtainTypedArray(R.array.back_bg_array)
            2-> context.resources.obtainTypedArray(R.array.middle_bg_array)
            else -> context.resources.obtainTypedArray(R.array.front_bg_array)
        }
        for(i in 0 until obtainTypedArray.length()) {
            bgResIds.add(obtainTypedArray.getResourceId(i, R.color.transparent))
        }
        obtainTypedArray.recycle()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(ImageView(context))

    override fun getItemCount(): Int = bgResIds.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(bgResIds[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv = view as ImageView

        fun bind(resId: Int) {
            val picWidth = getDrawableWidth(resId, iv)
            val ivWidth = (picWidth * screenHeight) / 750
            if (iv.layoutParams == null) {
                iv.layoutParams =
                    ViewGroup.LayoutParams(ivWidth, screenHeight)
            } else {
                iv.layoutParams.width = ivWidth
                iv.layoutParams.height = screenHeight
            }

//            Glide.with(context).load(resId).listener(object : RequestListener<Drawable> {
//                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean) = false
//
//                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
//                    resource?.apply {
//                        // 获得原始宽高比
//                        val widthXHeight = resource.intrinsicWidth * 1.0F / resource.intrinsicHeight
//                        if(iv.layoutParams == null) {
//                            iv.layoutParams = ViewGroup.LayoutParams((screenHeight * widthXHeight).toInt(),screenHeight)
//                        } else {
//                            iv.layoutParams.width = (screenHeight * widthXHeight).toInt()
//                            iv.layoutParams.height = screenHeight
//                        }
//                    }
//                    return false
//                }
//            }).into(iv)
        }
    }

    private fun getDrawableWidth(resId: Int, iv: ImageView): Int {
        val openRawResource = openRawResource(resId)
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = false
        options.inPreferredConfig = Bitmap.Config.RGB_565
        options.inPurgeable = true
        options.inInputShareable = true
        options.inSampleSize = 1
        val decodeStream = BitmapFactory.decodeStream(openRawResource, null, options)
        iv.setImageBitmap(decodeStream)
        val width = decodeStream!!.width
        try {
            openRawResource.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return width
    }

}
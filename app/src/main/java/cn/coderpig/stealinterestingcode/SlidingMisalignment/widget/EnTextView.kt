package cn.coderpig.stealinterestingcode.SlidingMisalignment.widget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import cn.coderpig.stealinterestingcode.R
import cn.coderpig.stealinterestingcode.getAssets

/**
 * 描述：
 *
 * @author jay on 2019-11-06 10:20
 */

class EnTextView : TextView {

    private var tf: String = "fonts/Quicksand.ttf"  // 默认字体包

    constructor(mContext: Context) : this(mContext, null)

    constructor(mContext: Context, attrs: AttributeSet?) : this(mContext, attrs, 0)

    constructor(mContext: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(mContext, attrs, defStyleAttr) {
        val obtainTypedArray = context.obtainStyledAttributes(attrs, R.styleable.EnTextView)
        val typeface = obtainTypedArray.getString(R.styleable.EnTextView_typeface)
        typeface?.let { tf = typeface }
        obtainTypedArray.recycle()
        // 设置字体
        setTypeface(Typeface.createFromAsset(getAssets(),tf))
    }
}
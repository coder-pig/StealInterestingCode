package cn.coderpig.stealinterestingcode.SlidingMisalignment.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import cn.coderpig.stealinterestingcode.falseLet

/**
 * @Author: CoderPig
 * @Description: 自定义虚线
 * @Date: Create in 下午 03:57 2019/10/17 0017
 */
class LineView : View {
    private lateinit var mPaint: Paint
    private lateinit var mPath: Path
    private var mWidth = 0
    private var mHeight = 0
    private var isUp = false

    constructor(mContext: Context) : this(mContext, null) {}

    constructor(mContext: Context, attrs: AttributeSet?) : this(mContext, attrs, 0) {}

    constructor(mContext: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(mContext, attrs, defStyleAttr) {
        setWillNotDraw(false)
        init()
    }

    private fun init() {
        this.mPaint = Paint()
        this.mPaint.color = Color.parseColor("#ffffff")
        this.mPaint.isAntiAlias = true
        this.mPaint.style = Paint.Style.STROKE
        this.mPaint.strokeWidth = 4.0f
        this.mPaint.isAntiAlias = true
        var path = Path()
        this.mPaint.pathEffect = PathDashPathEffect(path, 50.0f, 0.0f, PathDashPathEffect.Style.ROTATE);
        path.addCircle(0.0f, 0.0f, 12.0f, Path.Direction.CW)

    }

    override fun onDraw(canvas: Canvas?) {
        var i = 0
        super.onDraw(canvas)
        val i2: Float = (if (this.isUp) this.mHeight else 0).toFloat()
        this.isUp.falseLet { i = this@LineView.mHeight }
        this.mPath.moveTo(0.0f, i2)
        this.mPath.lineTo(this.mWidth.toFloat(), i.toFloat())
        canvas?.drawPath(this.mPath, this.mPaint)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        this.mWidth = width
        this.mHeight = height
    }

    fun setIsUp(isUp: Boolean) {
        this.isUp = isUp
        invalidate()
    }
}
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

    constructor(mContext: Context) : this(mContext, null)

    constructor(mContext: Context, attrs: AttributeSet?) : this(mContext, attrs, 0)

    constructor(mContext: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(mContext, attrs, defStyleAttr) { init() }

    private fun init() {
        this.mPaint = Paint()
        this.mPaint.color = Color.parseColor("#FFFFFF")
        this.mPaint.isAntiAlias = true
        this.mPaint.style = Paint.Style.STROKE
        this.mPaint.strokeWidth = 4.0f
        this.mPaint.isAntiAlias = true
        val path = Path()
        // 绘制圆心(0,0)，半径为12，顺时间方向
        path.addCircle(0.0f, 0.0f, 12.0f, Path.Direction.CW)
        // 用于将Path线段虚线化
        this.mPaint.pathEffect = PathDashPathEffect(path, 50.0f, 0.0f, PathDashPathEffect.Style.ROTATE)
        this.mPath = Path()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        this.mWidth = width
        this.mHeight = height
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val startY: Float = (if (this.isUp) this.mHeight else 0).toFloat() // 起始绘制Y坐标
        val endY = (if (this.isUp) 0 else this.mHeight).toFloat() // 结束绘制Y坐标
        this.mPath.moveTo(0.0f, startY) // 移动到起点
        this.mPath.lineTo(this.mWidth.toFloat(), endY)  // 绘制直线
        canvas?.drawPath(this.mPath, this.mPaint)
    }

    fun setIsUp(isUp: Boolean) {
        this.isUp = isUp
        invalidate()
    }
}
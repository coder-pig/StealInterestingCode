package cn.coderpig.stealinterestingcode

import android.content.Context.WINDOW_SERVICE
import android.graphics.Typeface
import android.view.WindowManager

/**
 * @Author: CoderPig
 * @Description: UI相关的工具集
 * @Date: Create in 下午 05:36 2019/10/16 0016
 */
fun dp2px(dpValue: Float): Int = (CPApp.instance.resources.displayMetrics.density * dpValue + 0.5f).toInt()

fun px2dp(pxValue: Float): Int = (CPApp.instance.resources.displayMetrics.density / pxValue + 0.5f).toInt()

fun px2sp(pxValue: Float): Int = (CPApp.instance.resources.displayMetrics.scaledDensity / pxValue + 0.5f).toInt()

fun sp2px(spValue: Float): Int = (CPApp.instance.resources.displayMetrics.scaledDensity * spValue + 0.5f).toInt()

fun getScreenWidth(): Int = CPApp.instance.resources.displayMetrics.widthPixels

fun getScreenHeight(): Int = CPApp.instance.resources.displayMetrics.heightPixels

fun getScreenHeightByService(): Int = (CPApp.instance.getSystemService(WINDOW_SERVICE) as WindowManager).defaultDisplay.height

fun getScreenDensity(): Float = CPApp.instance.resources.displayMetrics.density

fun getScreenDensityDpi(): Int = CPApp.instance.resources.displayMetrics.densityDpi

fun openRawResource(resId: Int) = CPApp.instance.resources.openRawResource(resId)

fun getAssets() = CPApp.instance.resources.assets

val mTf: Typeface = Typeface.createFromAsset(getAssets(),"fonts/Quicksand.ttf")
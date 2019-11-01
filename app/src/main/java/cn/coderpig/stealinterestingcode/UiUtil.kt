package cn.coderpig.stealinterestingcode

/**
 * @Author: CoderPig
 * @Description: UI相关的工具集
 * @Date: Create in 下午 05:36 2019/10/16 0016
 */

fun getScreenWidth(): Int = CPApp.instance.resources.displayMetrics.widthPixels

fun getScreenHeight(): Int = CPApp.instance.resources.displayMetrics.heightPixels



fun getScreenDensity(): Float = CPApp.instance.resources.displayMetrics.density

fun getScreenDensityDpi(): Int = CPApp.instance.resources.displayMetrics.densityDpi

fun openRawResource(resId: Int) = CPApp.instance.resources.openRawResource(resId)

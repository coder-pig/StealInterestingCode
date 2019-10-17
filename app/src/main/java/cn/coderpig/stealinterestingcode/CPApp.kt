package cn.coderpig.stealinterestingcode

import android.app.Application
import kotlin.properties.Delegates

/**
 * @Author: CoderPig
 * @Description:
 * @Date: Create in 下午 05:32 2019/10/16 0016
 */
open class CPApp: Application() {
    companion object {
        var instance by Delegates.notNull<CPApp>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
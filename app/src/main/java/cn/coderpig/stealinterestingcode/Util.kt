package cn.coderpig.stealinterestingcode

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

/**
 * @Author: CoderPig
 * @Description:
 * @Date: Create in 下午 03:43 2019/10/17 0017
 */

/* === 链式调用简化IF-ELSE ===*/
inline infix fun Boolean.trueLet(trueBlock: Boolean.() -> Unit): Else {
    if (this) {
        trueBlock()
        return NotDoElse(this)
    }
    return DoElse(this)
}

inline infix fun Boolean.falseLet(falseBlock: Boolean.() -> Unit): Else {
    if (!this) {
        falseBlock()
        return NotDoElse(this)
    }
    return DoElse(this)
}

interface Else {
    infix fun elseLet(elseBlock: Boolean.() -> Unit)
}

class DoElse(private val boolean: Boolean) : Else {
    override infix fun elseLet(elseBlock: Boolean.() -> Unit) {
        elseBlock(boolean)
    }
}

class NotDoElse(private val boolean: Boolean) : Else {
    override infix fun elseLet(elseBlock: Boolean.() -> Unit) {}
}

/* === Activity跳转扩展 ===*/
inline fun <reified T : Activity> FragmentActivity.fly(bundle: Bundle? = null, isFinish: Boolean = false) {
    val intent = Intent(this, T::class.java)
    bundle?.let {  intent.putExtras(bundle) }
    startActivity(intent)
    isFinish.trueLet { finish() }
}
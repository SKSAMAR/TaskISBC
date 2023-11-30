package com.example.taskiSBC.util

import android.content.Context
import android.os.SystemClock
import android.widget.Toast


object AppUtils {

    private var mLastClickTime: Long = 0
    fun isSafeClick(): Boolean {
        return if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            false
        } else {
            mLastClickTime = SystemClock.elapsedRealtime()
            true
        }
    }

    fun Context.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}
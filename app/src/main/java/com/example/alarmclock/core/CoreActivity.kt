package com.example.alarmclock.core

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager

class CoreActivity {
    companion object {
        fun setActivityFullScreen(activity: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                activity.window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
        }

        private fun setWindowFlag(bits: Int, on: Boolean, activity: Activity) {
            val win = activity.window
            val winParams = win.attributes
            if (on) {
                winParams.flags = winParams.flags or bits
            } else {
                winParams.flags = winParams.flags and bits.inv()
            }
            win.attributes = winParams
        }

        fun setTransparentStatusBar(activity: Activity){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false, activity)
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                activity.window.statusBarColor = Color.TRANSPARENT
            }else{
                setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true, activity)
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }
        }
    }
}
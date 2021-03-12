package vn.kien.tokoinchallenge.util

import android.util.Log
import vn.kien.tokoinchallenge.BuildConfig

object TokoinLogger {
    private val enabled = BuildConfig.DEBUG

    fun i(message: String) {
        if (enabled) Log.i("Tokoin Info", message)
    }

    fun e(message: String) {
        if (enabled) Log.e("Tokoin Error", message)
    }

    fun d(message: String) {
        if (enabled) Log.d("Tokoin Debug", message)
    }
}
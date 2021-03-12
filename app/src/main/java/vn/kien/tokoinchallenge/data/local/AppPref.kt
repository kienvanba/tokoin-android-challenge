package vn.kien.tokoinchallenge.data.local

import android.content.Context

class AppPref(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
}
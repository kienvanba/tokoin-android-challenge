package vn.kien.tokoinchallenge.data.local

import android.content.Context
import com.google.gson.Gson
import vn.kien.tokoinchallenge.model.User

class AppPref(context: Context, private val gson: Gson) {
    private val sharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    companion object {
        private const val USER = "_user"
    }

    var user: User?
        get() {
            val jsonString = sharedPreferences.getString(USER, null)
            if (jsonString != null) {
                return gson.fromJson(jsonString, User::class.java)
            }
            return null
        }
        set(value) {
            if (value != null) {
                val jsonString = gson.toJson(value)
                sharedPreferences.edit().putString(USER, jsonString).apply()
            } else {
                sharedPreferences.edit().remove(USER).apply()
            }
        }
}
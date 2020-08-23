package com.arkapp.rozassingment.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.arkapp.rozassingment.data.models.Users
import com.arkapp.rozassingment.data.prefrences.PREFERENCE_NAME
import com.arkapp.rozassingment.data.prefrences.PREF_LOGGED_IN
import com.arkapp.rozassingment.data.prefrences.PREF_USER
import com.google.gson.Gson


class PrefRepository(val context: Context) {

    private val pref: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val editor = pref.edit()
    private val gson = Gson()

    private fun String.put(long: Long) {
        editor.putLong(this, long)
        editor.commit()
    }

    private fun String.put(int: Int) {
        editor.putInt(this, int)
        editor.commit()
    }

    private fun String.put(string: String) {
        editor.putString(this, string)
        editor.commit()
    }

    private fun String.put(boolean: Boolean) {
        editor.putBoolean(this, boolean)
        editor.commit()
    }


    private fun String.getLong(): Long {
        return pref.getLong(this, 0)
    }

    private fun String.getInt(): Int {
        return pref.getInt(this, 0)
    }

    private fun String.getString(): String {
        return pref.getString(this, "")!!
    }

    private fun String.getBoolean(): Boolean {
        return pref.getBoolean(this, false)
    }

    /*********************************************/

    fun clearData() {
        editor.clear()
        editor.commit()
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        PREF_LOGGED_IN.put(isLoggedIn)
    }

    fun isLoggedIn() = PREF_LOGGED_IN.getBoolean()

    fun setUser(userData: Users) {
        PREF_USER.put(gson.toJson(userData))
    }

    fun getUser() = gson.fromJson(PREF_USER.getString(), Users::class.java)
}
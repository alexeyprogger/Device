package com.futuremed.pacient

import android.content.Context

const val STORAGE_NAME = "FUTUREMED_APP_SETTINGS"
const val FIRST_START_FLAG = "FIRST_START_FLAG"
const val USER_ID = "USER_ID"

class AppSettings(context: Context) {

    private var settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
    private var editor = settings.edit()

    fun isFirstStart(): Boolean {
        return settings.getBoolean(FIRST_START_FLAG, true)
    }

    fun changeFirstStart() {
        editor.putBoolean(FIRST_START_FLAG, false).apply()
    }

    fun getUserId(): Int {
        return settings.getInt(USER_ID, 1)
    }

    fun saveUserId(id: Int) {
        editor.putInt(USER_ID, id).apply()
    }


}
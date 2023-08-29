package com.richard.vendingmachineapp.data.preferences

import android.content.Context

class PreferenceHelper(private val context: Context) {

    fun saveBalance(balance: Int) {
        val preferences = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt(BALANCE_KEY, balance)
        editor.apply()
    }

    fun getBalance(): Int {
        val preferences = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE)
        return preferences.getInt(BALANCE_KEY, 0)
    }

    companion object {
        private const val BALANCE_KEY = "balance_key"
        private const val PACKAGE_NAME = "com.richard.vendingmachineapp"
    }

}
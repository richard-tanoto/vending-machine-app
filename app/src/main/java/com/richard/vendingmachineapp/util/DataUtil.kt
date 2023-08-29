package com.richard.vendingmachineapp.util

import android.content.Context
import com.richard.vendingmachineapp.R
import com.richard.vendingmachineapp.data.ItemDao
import com.richard.vendingmachineapp.data.model.Item
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object DataUtil {

    fun fillWithStartingData(context: Context, dao: ItemDao) {
        val items = loadJsonArray(context)
        try {
            if (items != null) {
                for (i in 0 until items.length()) {
                    val item = items.getJSONObject(i)
                    dao.insertItems(
                        Item(
                            item.getInt("id"),
                            item.getString("name"),
                            item.getLong("price"),
                            item.getInt("stock"),
                            item.getString("imageUrl")
                        )
                    )
                }
            }
        } catch (exception: JSONException) {
            exception.printStackTrace()
        }
    }

    private fun loadJsonArray(context: Context): JSONArray? {
        val builder = StringBuilder()
        val `in` = context.resources.openRawResource(R.raw.item)
        val reader = BufferedReader(InputStreamReader(`in`))
        var line: String?
        try {
            while (reader.readLine().also { line = it } != null) {
                builder.append(line)
            }
            val json = JSONObject(builder.toString())
            return json.getJSONArray("items")
        } catch (exception: IOException) {
            exception.printStackTrace()
        } catch (exception: JSONException) {
            exception.printStackTrace()
        }
        return null
    }
}
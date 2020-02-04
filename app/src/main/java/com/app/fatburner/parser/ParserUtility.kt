package com.app.fatburner.parser

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.app.fatburner.model.HealthTip
import com.google.gson.GsonBuilder
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

const val  TAG = "PARSER"
fun loadProfiles(context: Context): List<HealthTip>? {
    try {
        val builder = GsonBuilder()
        val gson = builder.create()
        val array = JSONArray(loadJSONFromAsset(context, "heathtip.json"))
        val profileList = ArrayList<HealthTip>()
        for (i in 0 until array.length()) {
            val profile = gson.fromJson(array.getString(i), HealthTip::class.java)
            profileList.add(profile)
        }
        return profileList
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }

}

private fun loadJSONFromAsset(context: Context, jsonFileName: String): String? {
    var json: String? = null
    var `is`: InputStream? = null
    try {
        val manager = context.getAssets()
        Log.d(TAG, "path $jsonFileName")
        `is` = manager.open(jsonFileName)
        val size = `is`!!.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        json = String(buffer, Charset.forName("UTF-8"))
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    }

    return json
}
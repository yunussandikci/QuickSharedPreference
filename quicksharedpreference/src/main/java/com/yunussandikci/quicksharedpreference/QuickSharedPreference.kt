package com.yunussandikci.quicksharedpreference

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.HashMap

object QuickSharedPreference {

    @Volatile
    private var cache = HashMap<String, String?>()

    @Volatile
    private var useCache = true

    private fun getSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun <T> get(context: Context, key: String, type: Class<T>): T? {
        val jsonValue: String
        if(cache[key] != null && useCache){
            jsonValue = cache[key].orEmpty()
        } else {
            jsonValue = getSharedPreference(context).getString(key, null).orEmpty()
            cache[key] = jsonValue
        }
        return Gson().fromJson(jsonValue, type)
    }

    fun <T> set(context: Context, key: String, value: T): Boolean {
        val sharedPreferencesEditor = getSharedPreference(context).edit()
        val jsonValue = Gson().toJson(value)
        if (useCache) {
            cache[key] = jsonValue.toString()
        }
        return sharedPreferencesEditor.putString(key, jsonValue).commit()
    }

    fun <T> setAsync(context: Context, key: String, value: T) {
        val sharedPreferencesEditor = getSharedPreference(context).edit()
        val jsonValue = Gson().toJson(value)
        if (useCache) {
            cache[key] = jsonValue.toString()
        }
        sharedPreferencesEditor.putString(key, jsonValue).apply()
    }

    fun remove(context: Context, key: String): Boolean {
        val sharedPreferencesEditor = getSharedPreference(context).edit()
        if(useCache) {
            cache[key] = null
        }
        return sharedPreferencesEditor.remove(key).commit()
    }

    fun removeAsync(context: Context, key: String) {
        val sharedPreferencesEditor = getSharedPreference(context).edit()
        if(useCache) {
            cache[key] = null
        }
        sharedPreferencesEditor.remove(key).apply()
    }

    fun removeAll(context: Context): Boolean {
        clearCache()
        return getSharedPreference(context).edit().clear().commit()
    }

    fun removeAllAsync(context: Context) {
        clearCache()
        return getSharedPreference(context).edit().clear().apply()
    }

    fun clearCache() {
        cache = HashMap()
    }

    fun setUseCache(boolean: Boolean) {
        this.useCache = boolean
        if (!useCache) {
            clearCache()
        }
    }
}
package com.nick_sib.beauty_radar.view_model.shared_preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SharedPreferences(context: Context) {

    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    var paramString by prefs.string(
        key = { "KEY_PARAM3" },
        defaultValue = "default"
    )
    var paramStringNullable by prefs.stringNullable()
    var paramInt by prefs.int()

    fun SharedPreferences.string(
        defaultValue: String = "",
        key: (KProperty<*>) -> String = KProperty<*>::name
    ): ReadWriteProperty<Any, String> =
        object : ReadWriteProperty<Any, String> {
            override fun getValue(
                thisRef: Any,
                property: KProperty<*>
            ): String = getString(key(property), defaultValue).toString()

            override fun setValue(
                thisRef: Any,
                property: KProperty<*>,
                value: String
            ) = edit().putString(key(property), value).apply()
        }

    fun SharedPreferences.stringNullable(
        defaultValue: String? = null,
        key: (KProperty<*>) -> String = KProperty<*>::name
    ): ReadWriteProperty<Any, String?> =
        object : ReadWriteProperty<Any, String?> {
            override fun getValue(
                thisRef: Any,
                property: KProperty<*>
            ) = getString(key(property), defaultValue)

            override fun setValue(
                thisRef: Any,
                property: KProperty<*>,
                value: String?
            ) = edit().putString(key(property), value).apply()
        }

    fun SharedPreferences.int(
        defaultValue: Int = 0,
        key: (KProperty<*>) -> String = KProperty<*>::name
    ): ReadWriteProperty<Any, Int> =
        object : ReadWriteProperty<Any, Int> {
            override fun getValue(
                thisRef: Any,
                property: KProperty<*>
            ) = getInt(key(property), defaultValue)

            override fun setValue(
                thisRef: Any,
                property: KProperty<*>,
                value: Int
            ) = edit().putInt(key(property), value).apply()
        }
}



package com.pierfrancescosoffritti.configurablethreejsapp.testclient

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.widget.EditText

class PreferencesManager(private val context: Context, private val ipEditText: EditText, private val portEditText: EditText) : LifecycleObserver {
    private val preferenceKeyIp = "ip"
    private val preferenceKeyPort = "port"
    private val sharedPreferencesKey = "MainActivity_SharedPreferences"

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        val prefs = context.getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE)
        ipEditText.setText(prefs.getString(preferenceKeyIp, ""))
        portEditText.setText(prefs.getString(preferenceKeyPort, ""))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        val prefs = context.getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE)
        prefs.edit()
                .putString(preferenceKeyIp, ipEditText.text.toString())
                .putString(preferenceKeyPort, portEditText.text.toString())
                .apply()
    }
}
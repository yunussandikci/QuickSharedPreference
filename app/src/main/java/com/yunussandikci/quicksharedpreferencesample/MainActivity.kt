package com.yunussandikci.quicksharedpreferencesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.yunussandikci.quicksharedpreference.QuickSharedPreference

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QuickSharedPreference.set(this, "hiMessage", "Hello World From Shared Preference")
        setContentView(R.layout.activity_main)
        String
        findViewById<TextView>(R.id.hello).text =
            QuickSharedPreference.get(this, "hiMessage", String::class.java)
    }
}

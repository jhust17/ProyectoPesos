package com.proy.kmanager1

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.proy.kmanager1.util.PreferenceHelper
import com.proy.kmanager1.util.PreferenceHelper.get
import com.proy.kmanager1.util.PreferenceHelper.set

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferences = PreferenceHelper.defaultPrefs(this)
        if (preferences["session", false])
            gotoMenu()

        val GoRegister = findViewById<TextView>(R.id.registro)
        GoRegister.setOnClickListener {
            gotoRegister()
        }
        val GoMenu = findViewById<Button>(R.id.btn_sig)
        GoMenu.setOnClickListener {
            gotoMenu()
        }
    }

    private fun gotoRegister() {
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }
    private fun gotoMenu() {
        val i = Intent(this, ProfilesActivity::class.java)
        createSessionPreference()
        startActivity(i)
        finish()
    }

    private fun createSessionPreference(){
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["session"] = true
    }
}
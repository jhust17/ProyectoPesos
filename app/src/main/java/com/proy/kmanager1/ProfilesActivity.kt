package com.proy.kmanager1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.proy.kmanager1.util.PreferenceHelper
import com.proy.kmanager1.util.PreferenceHelper.set

class ProfilesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profiles)

        val goRegUser = findViewById<FloatingActionButton>(R.id.add_profile)
        goRegUser.setOnClickListener {
            gotoRegUser()
        }

        val btlogout = findViewById<Button>(R.id.btlogout)
        btlogout.setOnClickListener {
            clearSessionPreference()
            gotoLogin()
        }

    }

    private fun gotoRegUser() {
        val i = Intent(this, RegProfileActivity::class.java)
        startActivity(i)
    }

    private fun gotoLogin(){
        val i = Intent(this, MainActivity::class.java)

        startActivity(i)
        finish()
    }
    private fun clearSessionPreference(){
        val preference = PreferenceHelper.defaultPrefs(this)
        preference["session"]= false
    }
}
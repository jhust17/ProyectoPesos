package com.proy.kmanager1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.proy.kmanager1.R
import com.proy.kmanager1.io.ApiService
import com.proy.kmanager1.io.response.LoginResponse
import com.proy.kmanager1.io.response.LogoutResponse
import com.proy.kmanager1.model.Logg
import com.proy.kmanager1.util.PreferenceHelper
import com.proy.kmanager1.util.PreferenceHelper.set
import com.proy.kmanager1.util.PreferenceHelper.get
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilesActivity : AppCompatActivity() {

    private val apiService: ApiService by lazy {
        ApiService.create()
    }
    private val preferences by lazy {
        PreferenceHelper.defaultPrefs(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profiles)

        val preferences = PreferenceHelper.defaultPrefs(this)

        val goRegUser = findViewById<FloatingActionButton>(R.id.add_profile)
        goRegUser.setOnClickListener {
            gotoRegUser()
        }

        val btlogout = findViewById<Button>(R.id.btlogout)
        btlogout.setOnClickListener {
            performLogout()
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

    private fun performLogout(){
        val body = preferences["body", ""]
        val call = apiService.postLogout("Bearer $body")
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                clearSessionPreference()
                gotoLogin()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(applicationContext, "Error en el servidor", Toast.LENGTH_SHORT).show()
            }

        })

    }
    private fun clearSessionPreference(){
        preferences["body"] = ""
    }

}


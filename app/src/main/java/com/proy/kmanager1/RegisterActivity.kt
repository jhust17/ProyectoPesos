package com.proy.kmanager1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val goLogin = findViewById<TextView>(R.id.yaRegistrado)
        goLogin.setOnClickListener {
            gotoLogin()
        }
    }

    private fun gotoLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}
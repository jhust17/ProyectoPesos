package com.proy.kmanager1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.proy.kmanager1.R
import com.proy.kmanager1.io.ApiService
import com.proy.kmanager1.io.response.LoginResponse
import com.proy.kmanager1.model.Logg
import com.proy.kmanager1.model.User
import com.proy.kmanager1.util.PreferenceHelper
import com.proy.kmanager1.util.PreferenceHelper.get
import com.proy.kmanager1.util.PreferenceHelper.set
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val apiService: ApiService by lazy {
        ApiService.create()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val preferences = PreferenceHelper.defaultPrefs(this)
        if (preferences["body", ""].contains("."))
            gotoMenu()

        val GoRegister = findViewById<TextView>(R.id.registro)
        GoRegister.setOnClickListener {
            gotoRegister()
        }
        val GoMenu = findViewById<Button>(R.id.btn_sig)
        GoMenu.setOnClickListener {
            performLogin()
        }
    }

    private fun gotoRegister() {
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }
    private fun gotoMenu() {
        val i = Intent(this, ProfilesActivity::class.java)

        startActivity(i)
        finish()
    }

    private fun createSessionPreference(body: String){
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["body"] = body
    }

    private fun performLogin(){
        val usuario = findViewById<EditText>(R.id.email).text.toString()
        val pwd = findViewById<EditText>(R.id.pwd).text.toString()
        if(usuario.trim().isEmpty() || pwd.trim().isEmpty()){
            Toast.makeText(applicationContext, "Campos Vacios", Toast.LENGTH_SHORT).show()
            return
        }
        val logg = Logg(usuario, pwd)
        val call =  apiService.postLogin(logg)
        call.enqueue(object: Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    val loginResponse = response.body()
                    if(loginResponse == null){
                        Toast.makeText(applicationContext, "Se produjo un error en el servidor1", Toast.LENGTH_SHORT).show()
                        return
                    }
                    if (loginResponse.error == false){
                        createSessionPreference(loginResponse.body)
                        gotoMenu()
                    }else{

                        Toast.makeText(applicationContext, "Credenciales Incorrectas2", Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Toast.makeText(applicationContext, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Se produjo un error en el servidor3", Toast.LENGTH_SHORT).show()
            }

        })
    }
}
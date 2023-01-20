package com.proy.kmanager1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RegProfileActivity : AppCompatActivity() {
    private lateinit var txtNom: EditText
    private lateinit var txtEda: EditText
    private lateinit var txtAlt: EditText
    private lateinit var txtPes: EditText
    private lateinit var txtGenH: RadioButton
    private lateinit var txtGenM: RadioButton
    private lateinit var txtFum: CheckBox
    private lateinit var adapter: ListElementAdapter


    var nombre: String =("")
    var edad: Int = 0
    var altura: Int = 0
    var peso: Int =0
    var genero: Int =0
    var fuma: Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_profile)
        txtNom= findViewById(R.id.nomTxt)
        txtEda= findViewById(R.id.edadTxt)
        txtAlt= findViewById(R.id.alturaTxt)
        txtPes= findViewById(R.id.pesoTxt)
        txtGenH= findViewById(R.id.radioButtonH)
        txtGenM= findViewById(R.id.radioButtonM)
        txtFum= findViewById(R.id.checkFum)
    }

    fun Aregistro(view: View) {
            validar()

    }
    private fun validar(){
        if(TextUtils.isEmpty(txtNom.text.toString())){
            txtNom.error="Ingrese  nombre "
            txtNom.requestFocus()
            return
        }else{
             nombre= txtNom.text.toString()
        }
        if(TextUtils.isEmpty(txtEda.text.toString())){
            txtEda.error="Ingrese edad"
            txtEda.requestFocus()
            return
        }else{
             edad = txtEda.text.toString().toInt()
        }
        if(TextUtils.isEmpty(txtAlt.text.toString())){
            txtAlt.error="Ingrese altura"
            txtAlt.requestFocus()
            return
        }else{
             altura = txtAlt.text.toString().toInt()
        }
        if(TextUtils.isEmpty(txtPes.text.toString())){
            txtPes.error="Ingrese peso"
            txtPes.requestFocus()
            return
        }else{
            peso = txtPes.text.toString().toInt()
        }
        if (txtGenH?.isChecked==true){
            genero = 1
        }else{
            genero = 2
        }
        if (txtFum?.isChecked==true){
            fuma =1
        }else{
            fuma=0
        }
        val  user = listOf<ListElement>(ListElement(null,nombre,edad,altura,peso,genero,fuma))
        val intent = Intent(this@RegProfileActivity, RecyclerV::class.java)
        intent.putExtra("nombre", nombre)
        intent.putExtra("edad", edad)
        intent.putExtra("altura", altura)
        intent.putExtra("peso", peso)
        intent.putExtra("genero", genero)
        intent.putExtra("fuma", fuma)
        startActivity(intent)
    }

    private fun getRetrofit(): Retrofit {
        val Base_url ="https://private-47aaaa-jhust17.apiary-mock.com/"
        return Retrofit.Builder()
            .baseUrl(Base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun userPost() {
        val call: Call<List<ListElement>> = getRetrofit().create(APIService::class.java).getUsers()
        call.enqueue(object : Callback<List<ListElement>> {
            override fun onResponse(
                call: Call<List<ListElement>>,
                response: Response<List<ListElement>>
            ) {
                if (response.isSuccessful){
                    val user:List<ListElement>? = response.body()
                    val users: List<ListElement> = user?: emptyList()
                    ListElementProvider.userelelist.clear()
                    ListElementProvider.userelelist.addAll(users)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<ListElement>>, t: Throwable) {
                showError()
            }
        })

    }
    private fun showError() {
        Toast.makeText(this, "Error no carga", Toast.LENGTH_SHORT).show()
    }

    fun Rrecycler(view: View){
        super.onBackPressed()
    }
}
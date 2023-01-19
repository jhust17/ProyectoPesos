package com.proy.kmanager1

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

class Historialp : AppCompatActivity() {
    private lateinit var adapter: PesosAdapter
    private lateinit var txtpeso: EditText
    val lista = mutableListOf<HistorialElement>()
    var nom: String = ""
    var Npeso: Int =0

    @RequiresApi(Build.VERSION_CODES.O)
    val currentDate = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historialp)
        txtpeso=findViewById(R.id.ApesoTxt)
        nom = intent.getStringExtra("nombre").toString()
        initRecyclerView()
        val datos = filterList(nom)
        ListElementProvider.pesosxid.clear()
        ListElementProvider.pesosxid.addAll(datos)
        lista.addAll(ListElementProvider.pesosxid)
        adapter.updateListpesos(lista)
    }

    fun filterList(name: String): List<HistorialElement> {
        return ListElementProvider.historialist.filter { it.nombre.startsWith(name) }
    }

    private fun initRecyclerView() {
        adapter = PesosAdapter(ListElementProvider.historialist) { HistorialElement ->
            onItemSelected(HistorialElement)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.listaH)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    fun onItemSelected(listElement: HistorialElement) {
        val toast =
            Toast.makeText(applicationContext, listElement.peso.toString(), Toast.LENGTH_SHORT)
        toast.show()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun Ahistorial(view: View) {
        if(TextUtils.isEmpty(txtpeso.text.toString())){
            txtpeso.error="Ingrese  el peso"
            txtpeso.requestFocus()
            return
        }else{
           Npeso= txtpeso.text.toString().toInt()
          }
        val pesosN = listOf<HistorialElement>(HistorialElement(nom,Npeso, currentDate))
        ListElementProvider.historialist.addAll(0,pesosN)
        lista.addAll(0,pesosN)
        adapter.notifyItemInserted(0)

    }

    fun Rhistorial(view: View) {
        super.onBackPressed()
    }
}
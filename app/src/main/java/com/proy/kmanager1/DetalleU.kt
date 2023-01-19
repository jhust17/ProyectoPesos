package com.proy.kmanager1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.time.LocalDate

class DetalleU : AppCompatActivity() {
    private lateinit var labelNom: TextView
    private lateinit var labelEda: TextView
    private lateinit var labelAtr: TextView
    private lateinit var labelPes: TextView
    private lateinit var labelGen: TextView
    private lateinit var labelFum: TextView
    var nom: String = ""
    var eda: Int = 0
    var alt: Int = 0
    var pes: Int = 0
    var gen: Int = 0
    var fum: Int = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_u)

        initDetalle()

    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun initDetalle() {
        labelNom = findViewById(R.id.nomTV)
        labelEda = findViewById(R.id.edaTV)
        labelAtr = findViewById(R.id.altTV)
        labelPes = findViewById(R.id.pesoTV)
        labelGen = findViewById(R.id.geneTV)
        labelFum = findViewById(R.id.fumTV)

        nom = intent.getStringExtra("Nombre").toString()
        eda = intent.getIntExtra("Edad", 0)
        alt = intent.getIntExtra("Altura", 0)
        pes = intent.getIntExtra("Peso", 0)
        gen = intent.getIntExtra("Genero",0)
        fum = intent.getIntExtra("Fuma",0)

        labelNom.setText(nom)
        labelEda.setText(eda.toString())
        labelAtr.setText(alt.toString())
        labelPes.setText(pes.toString())
        if (gen==1){
            labelGen.setText("Hombre")
        }else{
            labelGen.setText("Mujer")
        }
        if (fum==1){
            labelFum.setText("Si")
        }else{
            labelFum.setText("No")
        }
        val currentDate = LocalDate.now()

        ListElementProvider.historialist.add(HistorialElement(nom,pes,currentDate))
    }

    fun anterior(view: View) {
        super.onBackPressed()
    }


    fun Mhistorial(view: View) {
        val intent = Intent(this@DetalleU, Historialp::class.java)
        intent.putExtra("nombre", nom)
        startActivity(intent)

    }
}
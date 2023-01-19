package com.proy.kmanager1

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.time.LocalDate

class MainActivity2 : AppCompatActivity() {
    private lateinit var labeln:TextView
    private lateinit var labelf:TextView
    private lateinit var labelp:TextView
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        labelf=findViewById(R.id.fechass)
        labelp=findViewById(R.id.pesoss)
        labeln=findViewById(R.id.nombress)
        val nom = intent.getStringExtra("nombre").toString()
        val pes = intent.getIntExtra("peso", 0)
        val currentDate = LocalDate.now()
        labelf.setText(currentDate.toString())
        labelp.setText(pes.toString())
        labeln.setText(nom.toString())
    }
}
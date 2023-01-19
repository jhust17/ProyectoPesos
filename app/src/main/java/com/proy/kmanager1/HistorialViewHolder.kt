package com.proy.kmanager1

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistorialViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val peso = view.findViewById<TextView>(R.id.pesosTV)
    val date = view.findViewById<TextView>(R.id.fechaTV)

    fun render(usermodel: HistorialElement, onClickListener: (HistorialElement) -> Unit) {
        peso.text = usermodel.peso.toString()
        date.text = usermodel.fecha.toString()
        itemView.setOnClickListener { onClickListener(usermodel) }
    }
}
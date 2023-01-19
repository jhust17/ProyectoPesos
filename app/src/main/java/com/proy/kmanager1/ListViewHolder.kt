package com.proy.kmanager1

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val edad = view.findViewById<TextView>(R.id.edaTV)
    val nombre = view.findViewById<TextView>(R.id.nomTV)

    fun render(usermodel: ListElement, onClickListener: (ListElement) -> Unit) {
        edad.text = usermodel.age.toString()
        nombre.text = usermodel.name
        itemView.setOnClickListener { onClickListener(usermodel) }
    }
}
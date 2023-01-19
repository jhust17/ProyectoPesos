package com.proy.kmanager1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class PesosAdapter(
    private var pesoslist: List<HistorialElement>,
    private val onClickListener: (HistorialElement) -> Unit
) : RecyclerView.Adapter<HistorialViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HistorialViewHolder(layoutInflater.inflate(R.layout.items_pesos, parent, false))
    }

    override fun onBindViewHolder(holder: HistorialViewHolder, position: Int) {
        val item = pesoslist[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = pesoslist.size

    fun updateListpesos (pesoslist: List<HistorialElement>) {
        this.pesoslist = pesoslist
        notifyDataSetChanged()
    }
}



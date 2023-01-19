package com.proy.kmanager1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class ListElementAdapter(
    private var userlist: List<ListElement>,
    private val onClickListener: (ListElement) -> Unit
) : RecyclerView.Adapter<ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListViewHolder(layoutInflater.inflate(R.layout.items_users, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = userlist[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = userlist.size

    fun updateListUsers(userlist: List<ListElement>) {
        this.userlist = userlist
        notifyDataSetChanged()
    }
}
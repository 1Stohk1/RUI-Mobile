package com.mario.rui.ui.notifications

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mario.rui.R


class NotificationAdapter(private var rowList: MutableList<String>, private val call: NotificationsFragment) : RecyclerView.Adapter<NotificationAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val row : View = view.findViewById(R.id.row)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.notification_list, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val row = rowList[position]
        holder.name.text = row
        //TODO cambiare bg in base ad un parametro
        holder.row.setBackgroundResource(R.drawable.blu_card_edge)
        holder.row.setOnClickListener{ call.onClickListener(row) }
    }

    override fun getItemCount(): Int {
        return rowList.size
    }

    fun setList(list: MutableList<String>) {
        this.rowList = list
        notifyDataSetChanged()
    }

    interface Callback{
        fun onClickListener(task: String)
    }
}
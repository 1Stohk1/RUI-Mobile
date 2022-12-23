package com.mario.rui.ui.home.rec_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mario.rui.R
import com.mario.rui.model.Task


class TaskRVAdapter(private var rowList: MutableList<Task>, private val call: Callback) : RecyclerView.Adapter<TaskRVAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val photo : ImageView = view.findViewById(R.id.photo)
        val distance : TextView = view.findViewById(R.id.distance)
        val row : View = view.findViewById(R.id.row)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.row_list, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val row = rowList[position]
        holder.name.text = row.text
        holder.distance.text = row.owner

        Glide.with(holder.itemView.context)
            .load("https://www.ilnapolista.it/wp-content/uploads/2022/07/Mario_Rui_MG0_3984-e1658081286947.jpg")
            .into(holder.photo);

        holder.row.setOnClickListener{ call.onClickListener(row) }
    }

    override fun getItemCount(): Int {
        return rowList.size
    }

    fun setList(list: MutableList<Task>) {
        this.rowList = list
        notifyDataSetChanged()
    }

    interface Callback{
        fun onClickListener(task: Task)
    }
}
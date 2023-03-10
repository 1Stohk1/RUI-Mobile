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
import com.mario.rui.network.PropicManager


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

        val urlProPic = row.owner?.let { PropicManager.getPropicUrl(it) }
        Glide.with(holder.itemView.context)
            .load(urlProPic)
            .circleCrop()
            .into(holder.photo)

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
package com.mario.rui.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mario.rui.databinding.FragmentHomeBinding
import com.mario.rui.model.Task
import com.mario.rui.network.extractTaskList
import com.mario.rui.ui.home.rec_view.TaskRVAdapter
import java.util.*
import kotlin.collections.HashMap

@Suppress("UNCHECKED_CAST")
class HomeFragment : Fragment(),TaskRVAdapter.Callback {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    lateinit var rv: RecyclerView

    fun real_time_read(){
        val database = Firebase.database
        val myRef = database.getReference("tasks")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val taskList : MutableList<Task> = dataSnapshot.extractTaskList()
                rv = binding.rv
                val linearVertical = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                rv.layoutManager   = linearVertical
                rv.isNestedScrollingEnabled = false
                Objects.requireNonNull(rv.layoutManager!!).isAutoMeasureEnabled = true
                rv.adapter = TaskRVAdapter(taskList, this@HomeFragment)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Error: $error", Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        real_time_read()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickListener(task: Task) {
        val text = ("Clicked on ${task.text}")
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}
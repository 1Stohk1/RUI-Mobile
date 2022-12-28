package com.mario.rui.ui.notifications

import android.os.Bundle
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
import com.mario.rui.databinding.FragmentNotificationsBinding
import com.mario.rui.network.extractNotificationHistoryList
import java.util.*

class NotificationsFragment : Fragment(),NotificationAdapter.Callback {

    private var _binding: FragmentNotificationsBinding? = null

    private val binding get() = _binding!!

    lateinit var rv: RecyclerView

    fun real_time_read(){
        val database = Firebase.database
        val myRef = database.getReference("notification_history_workers")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val taskList = dataSnapshot.extractNotificationHistoryList()
                rv = binding.rv
                val linearVertical = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                rv.layoutManager   = linearVertical
                rv.isNestedScrollingEnabled = false
                Objects.requireNonNull(rv.layoutManager!!).isAutoMeasureEnabled = true
                rv.adapter = NotificationAdapter(taskList, this@NotificationsFragment)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Error: $error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        real_time_read()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickListener(notifica: String) {
        Toast.makeText(context, "Clicked: $notifica", Toast.LENGTH_SHORT).show()
    }


}
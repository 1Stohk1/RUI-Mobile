package com.mario.rui.network

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.mario.rui.model.Task
import java.util.ArrayList


fun DataSnapshot.extractTaskList() : MutableList<Task>{
    val taskList : MutableList<Task> = mutableListOf()

    val theMap = this.value as HashMap<String, String> // Obtained from Firebase
    for (key in theMap.keys) {
        val value = theMap[key] as HashMap<String, String>
        val owner = value["owner"]
        val text = value["text"]
        val status = value["status"] as Int?
        val task = Task(owner, text, status)
        taskList.add(task)
    }
    return taskList
}

fun DataSnapshot.extractNotificationHistoryList() : MutableList<String>{
    val taskList : MutableList<String> = mutableListOf()

    val theArrayList  = this.value // Obtained from Firebase
    Log.d("TAG", "extractNotificationHistoryList: $theArrayList")

    //Iterate theArrayList
    for (key in theArrayList as ArrayList<String>) {
        if(key != null){
            taskList.add(key)
        }
    }

    return taskList
}
package com.mario.rui

import android.annotation.SuppressLint
import com.mario.rui.network.APIClient.Companion.initialize
import android.app.Application;
import android.content.Context;
import com.google.firebase.FirebaseApp

class Init : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
        initialize(this)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null

        fun getContext(): Context? {
            return context
        }
    }
}
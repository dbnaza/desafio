package com.dbnaza.desafioenjoei

import android.app.Application

class App : Application() {
    companion object {
        @JvmStatic
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}
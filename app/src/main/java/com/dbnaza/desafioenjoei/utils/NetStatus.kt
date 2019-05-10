package com.dbnaza.desafioenjoei.utils

import android.content.Context
import android.net.ConnectivityManager

class NetStatus(private var context: Context) {
    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
}
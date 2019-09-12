package org.sushobh.exampleapp.Extras

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import io.reactivex.Single

class MobileInfo(var mContext : Context) {


    fun isNetworkAvailable() : Boolean {
        val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetwork: NetworkInfo? = null
        if (cm != null) {
            activeNetwork = cm.activeNetworkInfo
        }
        if(activeNetwork != null && activeNetwork.isConnectedOrConnecting){
            return true
        }
        else {
            return false
        }
    }

}
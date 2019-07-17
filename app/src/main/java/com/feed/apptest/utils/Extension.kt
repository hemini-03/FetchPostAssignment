package com.feed.Utility

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.annotation.RequiresPermission
import java.text.SimpleDateFormat
import java.util.*

fun String.convertDateInputFormatToGiveDateFormat(inputDateFormat:String, requiredDateFormat:String):String{
    val result: String
    val simpleDateFormatGivenDate: SimpleDateFormat
    val simpleDateFormatRequireDate: SimpleDateFormat
    try {
        simpleDateFormatGivenDate = SimpleDateFormat(inputDateFormat, Locale.getDefault())
        simpleDateFormatRequireDate = SimpleDateFormat(requiredDateFormat, Locale.getDefault())
        result = simpleDateFormatRequireDate.format(simpleDateFormatGivenDate.parse(this))
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
    return result
}

@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isConnected(): Boolean {
    val connectivityManager = this
        .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        val netInfo = it.activeNetworkInfo
        netInfo?.let {
            if (it.isConnected) return true
        }
    }
    return false
}

fun String.showToast(context:Context){
    Toast.makeText(context,this,Toast.LENGTH_SHORT).show()
}
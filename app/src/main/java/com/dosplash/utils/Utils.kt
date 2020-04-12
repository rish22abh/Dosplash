package com.dosplash.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.util.DisplayMetrics
import android.widget.ImageView
import com.dosplash.model.PhotosModel
import kotlin.math.roundToInt

class Utils {
    companion object{
        fun getDeviceWidth(aActivity: Activity?): Int {
            return try {
                val displayMetrics = DisplayMetrics()
                aActivity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
                return displayMetrics.widthPixels
            } catch (e: Exception) {
                0
            }
        }

        fun getDeviceHeight(aActivity: Activity?): Int {
            return try {
                val displayMetrics = DisplayMetrics()
                aActivity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
                return displayMetrics.heightPixels
            } catch (e: Exception) {
                0
            }
        }

        fun isNetworkAvailable(ctx: Context): Boolean {
            val connectivityManager = ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

        fun setImageDimenstion(
            mContext: Activity,
            mImage: ImageView,
            photosModel: PhotosModel
        ) {
            val width =
                getDeviceWidth(mContext)
            val ar:Float = (photosModel.height.toFloat() /photosModel.width.toFloat())
            val height = width * ar
            val layoutParams = mImage.layoutParams
            layoutParams.width = width
            layoutParams.height = height.roundToInt()

        }
    }
}
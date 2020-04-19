package com.dosplash.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.ImageView
import kotlin.math.roundToInt

class Utils {
    companion object{
        private fun getDeviceWidth(mContext: Context?): Int? {
            return try {
               return mContext?.resources?.displayMetrics?.widthPixels
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

        fun setImageDimen(
            mContext: Context,
            mImage: ImageView,
            mImageWidth: Int,
            mImageHeight: Int
        ) {
            val width = getDeviceWidth(mContext) ?: return
            val ar:Float = (mImageHeight.toFloat() /mImageWidth.toFloat())
            val height = width * ar
            val layoutParams = mImage.layoutParams
            layoutParams.width = width
            layoutParams.height = height.roundToInt()

        }
    }
}
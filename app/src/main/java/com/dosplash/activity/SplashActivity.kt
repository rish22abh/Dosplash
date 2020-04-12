package com.dosplash.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.dosplash.R

class SplashActivity : AppCompatActivity() {

    private var mHandler: Handler? = null
    private var mRunnable: Runnable = Runnable {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)

        mHandler = Handler()
    }

    override fun onResume() {
        super.onResume()
        mHandler?.postDelayed(mRunnable, 3000)
    }

    override fun onPause() {
        super.onPause()
        mHandler?.removeCallbacks(mRunnable)
    }
}

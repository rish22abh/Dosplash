package com.dosplash.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dosplash.R
import com.dosplash.databinding.ActivityDetailBinding
import com.dosplash.model.PhotosModel

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mDetailDataBinding: ActivityDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val photosModel = intent?.getParcelableExtra("model") as PhotosModel
        mDetailDataBinding.details = photosModel
        mDetailDataBinding.click = this
    }

    fun textVisibility(text: String?): Int {
        return if (text == null) View.GONE
        else View.VISIBLE
    }

    fun finishActivity() {
        finish()
    }
}
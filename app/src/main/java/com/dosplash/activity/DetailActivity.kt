package com.dosplash.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.dosplash.R
import com.dosplash.model.PhotosModel
import com.dosplash.utils.Utils
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val photosModel = intent?.getParcelableExtra("model") as PhotosModel

        Utils.setImageDimenstion(this, iv_image, photosModel)
        Glide.with(this).load(photosModel.urls?.full)
            .thumbnail(Glide.with(this).load(photosModel.urls?.thumb))
            .into(iv_image)

        Glide.with(this).load(photosModel.user?.profile_image?.large)
            .thumbnail(Glide.with(this).load(photosModel.user?.profile_image?.small))
            .transform(CircleCrop())
            .into(iv_profile);

        if (photosModel.user?.location != null) {
            textLocation.visibility = View.VISIBLE
            textLocation.text = photosModel.user?.location
        } else textLocation.visibility = View.GONE

        if (photosModel.user?.bio != null) {
            textDesc.visibility = View.VISIBLE
            textDesc.text = photosModel.user?.bio
        } else textDesc.visibility = View.GONE

        textUser.text = photosModel.user?.name

        iv_close.setOnClickListener { finish() }
    }
}
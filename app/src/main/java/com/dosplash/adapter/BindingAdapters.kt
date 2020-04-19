package com.dosplash.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.dosplash.utils.Utils

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("mainUrl", "thumbUrl", "imageWidth", "imageHeight")
    fun loadImage(
        mImageView: ImageView,
        mainUrl: String,
        thumbUrl: String,
        imageWidth: Int,
        imageHeight: Int
    ) {
        if (imageWidth != 0 && imageHeight != 0)
            Utils.setImageDimen(mImageView.context, mImageView, imageWidth, imageHeight)
        Glide.with(mImageView.context).load(mainUrl)
            .thumbnail(Glide.with(mImageView.context).load(thumbUrl))
            .into(mImageView)
    }

    @JvmStatic
    @BindingAdapter("mainUrl", "thumbUrl")
    fun loadImageWithCircleCrop(
        mImageView: ImageView,
        mainUrl: String,
        thumbUrl: String
    ) {
        Glide.with(mImageView.context).load(mainUrl)
            .thumbnail(Glide.with(mImageView.context).load(thumbUrl))
            .transform(CircleCrop())
            .into(mImageView)
    }

    @JvmStatic
    @BindingAdapter("textToBind")
    fun bindTextIfAvailable(mTextView: TextView, textToBind: String?) {
        mTextView.text = textToBind
    }
}
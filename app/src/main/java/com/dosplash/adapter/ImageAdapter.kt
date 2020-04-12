package com.dosplash.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.dosplash.R
import com.dosplash.activity.DetailActivity
import com.dosplash.model.PhotosModel
import com.dosplash.utils.Utils

class ImageAdapter(
    context: AppCompatActivity,
    photosList: ArrayList<PhotosModel>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val headerType = 0
    private val itemType = 1
    private val mContext = context
    private var mPhotosList: ArrayList<PhotosModel> = photosList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == headerType) HeaderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.adapter_header_layout,
                    parent,
                    false
                )
        )
        else ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_item_layout, parent, false)
        )
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mIvHeader: ImageView = view.findViewById(R.id.iv_header)
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mIvItem: ImageView = view.findViewById(R.id.iv_item)
        val mIvProfile: ImageView = view.findViewById(R.id.iv_profile)

        init {
            view.setOnClickListener {
                val intent = Intent(mContext, DetailActivity::class.java)
                intent.putExtra("model", mPhotosList.get(layoutPosition))
                mContext.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return mPhotosList.size;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            Utils.setImageDimenstion(mContext, holder.mIvHeader, mPhotosList[position])
            Glide.with(mContext).load(mPhotosList[position].urls?.regular)
                .thumbnail(Glide.with(mContext).load(mPhotosList[position].urls?.thumb))
                .into(holder.mIvHeader);
        } else if (holder is ItemViewHolder) {
            Utils.setImageDimenstion(mContext, holder.mIvItem, mPhotosList[position])
            Glide.with(mContext).load(mPhotosList[position].urls?.regular)
                .thumbnail(Glide.with(mContext).load(mPhotosList[position].urls?.thumb))
                .into(holder.mIvItem)
            Glide.with(mContext).load(mPhotosList[position].user?.profile_image?.medium)
                .thumbnail(
                    Glide.with(mContext).load(mPhotosList[position].user?.profile_image?.small)
                )
                .transform(CircleCrop())
                .into(holder.mIvProfile);
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) headerType
        else itemType
    }
}
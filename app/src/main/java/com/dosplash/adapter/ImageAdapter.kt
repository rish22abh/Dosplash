package com.dosplash.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dosplash.R
import com.dosplash.activity.DetailActivity
import com.dosplash.databinding.AdapterHeaderLayoutBinding
import com.dosplash.databinding.AdapterItemLayoutBinding
import com.dosplash.model.PhotosModel


class ImageAdapter(
    photosList: ArrayList<PhotosModel>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val headerType = 0
    private val itemType = 1
    private var mPhotosList: ArrayList<PhotosModel> = photosList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == headerType) {
            val mViewDataBinding: AdapterHeaderLayoutBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.adapter_header_layout, parent, false
            )
            HeaderViewHolder(mViewDataBinding)
        } else {
            val mViewDataBinding: AdapterItemLayoutBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.adapter_item_layout, parent, false
            )
            ItemViewHolder(mViewDataBinding)
        }
    }

    class HeaderViewHolder(val mAdapterHeaderLayoutBinding: AdapterHeaderLayoutBinding) :
        RecyclerView.ViewHolder(mAdapterHeaderLayoutBinding.root)

    class ItemViewHolder(val mAdapterItemLayoutBinding: AdapterItemLayoutBinding) :
        RecyclerView.ViewHolder(mAdapterItemLayoutBinding.root)

    override fun getItemCount(): Int {
        return mPhotosList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.mAdapterHeaderLayoutBinding.header = mPhotosList[position]
        } else if (holder is ItemViewHolder) {
            holder.mAdapterItemLayoutBinding.item = mPhotosList[position]
            holder.mAdapterItemLayoutBinding.click = this
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) headerType
        else itemType
    }


    fun intentDetailActivity(
        mView: View,
        model: PhotosModel
    ) {
        val intent = Intent(mView.context, DetailActivity::class.java)
        intent.putExtra("model", model)
        mView.context.startActivity(intent)
    }
}
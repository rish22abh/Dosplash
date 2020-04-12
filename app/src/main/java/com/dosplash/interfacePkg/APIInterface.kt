package com.dosplash.interfacePkg

import com.dosplash.model.PhotosModel
import retrofit2.Call
import retrofit2.http.*


interface APIInterface {
    @GET("/photos/random/")
    fun doGetRandomImage(): Call<PhotosModel?>?

    @GET("/photos/")
    fun doGetImageList(@Query("page") page:Int): Call<List<PhotosModel>?>?
}
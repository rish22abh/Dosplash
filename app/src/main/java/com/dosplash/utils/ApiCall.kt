package com.dosplash.utils

import com.dosplash.interfacePkg.ApiCallBack
import com.dosplash.model.PhotosModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCall {
    companion object {
        fun apiRandomCall(
            reqType: Int,
            callRandomImage: Call<PhotosModel?>?,
            apiCallBack: ApiCallBack
        ) {
            callRandomImage?.enqueue(object : Callback<PhotosModel?> {
                override fun onFailure(call: Call<PhotosModel?>, t: Throwable) {
                    apiCallBack.onRequestFail(reqType, t.message)
                }

                override fun onResponse(
                    call: Call<PhotosModel?>,
                    response: Response<PhotosModel?>
                ) {
                    if (response.code() == 403)
                        apiCallBack.onRequestFail(reqType,"Rate Limit Exceeded")
                    else if(response.isSuccessful)
                        apiCallBack.onRequestSuccess(reqType, response.body())
                }
            })
        }

        fun apiImageListCall(
            reqType: Int,
            callRandomImage: Call<List<PhotosModel>?>?,
            apiCallBack: ApiCallBack
        ) {
            callRandomImage?.enqueue(object : Callback<List<PhotosModel>?> {
                override fun onFailure(call: Call<List<PhotosModel>?>, t: Throwable) {
                    apiCallBack.onRequestFail(reqType, t.message)
                }

                override fun onResponse(
                    call: Call<List<PhotosModel>?>,
                    response: Response<List<PhotosModel>?>
                ) {
                    apiCallBack.onRequestSuccess(reqType, response.body())
                }
            })
        }
    }
}
package com.dosplash.interfacePkg

interface ApiCallBack {
    fun onRequestSuccess(reqType: Int, result: Any?)
    fun onRequestFail(reqType: Int, result: String?)
}
package com.dosplash.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.dosplash.R
import com.dosplash.adapter.ImageAdapter
import com.dosplash.interfacePkg.APIInterface
import com.dosplash.interfacePkg.ApiCallBack
import com.dosplash.model.PhotosModel
import com.dosplash.utils.APIClient
import com.dosplash.utils.ApiCall
import com.dosplash.utils.Constant
import com.dosplash.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call

class MainActivity : AppCompatActivity(), ApiCallBack, OnRefreshListener {
    private var mPhotosList: ArrayList<PhotosModel> = ArrayList()
    private var mImageAdapter: ImageAdapter? = null
    private var apiInterface: APIInterface? = null
    private var page = 0
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout.setOnRefreshListener(this);
        rv_image.layoutManager = LinearLayoutManager(this)
        mImageAdapter = ImageAdapter(mPhotosList)
        rv_image.adapter = mImageAdapter
        rv_image.addOnScrollListener(
            EndlessScrollListener(
                rv_image.layoutManager as LinearLayoutManager
            )
        )

        progressBarMain.visibility = View.VISIBLE
        apiInterface = APIClient.getClient()
            .create(APIInterface::class.java)
        callRandomImage()

    }

    override fun onRequestSuccess(reqType: Int, result: Any?) {
        when (reqType) {
            Constant.randomReqType -> {
                if (result is PhotosModel) {
                    val startingPoint = mPhotosList.size
                    mPhotosList.add(result)
                    mImageAdapter?.notifyItemRangeInserted(startingPoint, mPhotosList.size)
                    callPagination()

                }
            }
            Constant.photosReqType -> {
                if (result is List<*>) {
                    val startingPoint = mPhotosList.size
                    mPhotosList.addAll(result as List<PhotosModel>)
                    isLoading = false
                    progressBar.visibility = View.GONE
                    progressBarMain.visibility = View.GONE
                    swipeRefreshLayout.isRefreshing = false;
                    mImageAdapter?.notifyItemRangeInserted(startingPoint, mPhotosList.size)
                }
            }
        }
    }

    override fun onRequestFail(reqType: Int, result: String?) {
        isLoading = false
        progressBar.visibility = View.GONE
        progressBarMain.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = false;
        Toast.makeText(this, result, Toast.LENGTH_LONG).show()
    }


    override fun onRefresh() {
        callRandomImage()
    }


    inner class EndlessScrollListener(
        layoutManager: LinearLayoutManager
    ) : RecyclerView.OnScrollListener() {
        private var mLayoutManager: LinearLayoutManager = layoutManager
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val visibleItemCount: Int = mLayoutManager.childCount
            val firstVisibleItem: Int = mLayoutManager.findFirstVisibleItemPosition()
            val totalItemCount: Int = mLayoutManager.itemCount

            val l = visibleItemCount + firstVisibleItem
            if (l >= totalItemCount - 4 && !isLoading && mPhotosList.size > 1) {
                callPagination()
            }
        }
    }

    private fun callPagination() {
        if (Utils.isNetworkAvailable(this)) {
            isLoading = true
            progressBar.visibility = View.VISIBLE
            page += 1
            val callImageList: Call<List<PhotosModel>?>? = apiInterface?.doGetImageList(page)
            ApiCall.apiImageListCall(
                Constant.photosReqType,
                callImageList,
                this
            )
        } else onRequestFail(
            Constant.noInternetReqType, getString(
                R.string.text_no_internet
            ))

    }

    private fun callRandomImage() {
        if (Utils.isNetworkAvailable(this)) {
            page = 0
            val itemCount = mPhotosList.size;
            mPhotosList.clear()
            mImageAdapter?.notifyItemRangeRemoved(0, itemCount)
            val callRandomImage: Call<PhotosModel?>? = apiInterface?.doGetRandomImage()
            ApiCall.apiRandomCall(
                Constant.randomReqType,
                callRandomImage,
                this
            )
        } else onRequestFail(
            Constant.noInternetReqType, getString(
                R.string.text_no_internet
            )
        )
    }
}
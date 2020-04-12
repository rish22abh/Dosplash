package com.dosplash.utils

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class APIClient {
    companion object {
        fun getClient(): Retrofit {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(mInterceptor)

            val client = httpClient.build()
            return Retrofit.Builder()
                .baseUrl("https://api.unsplash.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        private var mInterceptor = Interceptor { chain ->
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("client_id", "od5QwQfqG1et95KAy8c41gFDLenGhdcew93HpgQ8nSw")
                .build()
            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)

            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
    }
}

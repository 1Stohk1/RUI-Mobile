package com.mario.rui.network

import com.mario.rui.BuildConfig
import com.mario.rui.Constants
import com.mario.rui.Init
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient private constructor(context: Init) {
    private var retrofitProd: Retrofit? = null

    companion object {
        private var instance: APIClient? = null
        @JvmStatic
        fun initialize(context: Init) {
            if (instance == null) {
                instance = APIClient(context)
            }
        }

        private fun getInstance(): APIClient {
            return instance!!
        }

        val serviceProdApi: API
            get() = getInstance().retrofitProd!!.create(API::class.java)
    }


    private fun createAdapter(context: Init) {
        val okhttp_builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging_interceptor = HttpLoggingInterceptor()
            logging_interceptor.apply {
                logging_interceptor.level = HttpLoggingInterceptor.Level.BODY
            }
            okhttp_builder.addInterceptor(logging_interceptor)
        }
        val timeOut: Long = 30
        okhttp_builder.connectTimeout(timeOut, TimeUnit.SECONDS)
        okhttp_builder.readTimeout(timeOut, TimeUnit.SECONDS)
        okhttp_builder.writeTimeout(timeOut, TimeUnit.SECONDS)
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        val cache = Cache(context.cacheDir, cacheSize.toLong())
        okhttp_builder.cache(cache)
        retrofitProd = Retrofit.Builder()
            .baseUrl(Constants.BASEURL_PROD)
            .client(okhttp_builder.build())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }


    init {
        createAdapter(context)
    }
}
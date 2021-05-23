package com.techno.myapplication.retrofit.repository

import com.techno.myapplication.retrofit.interfaces.ICoreServices
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitRepository {


    fun getEndPointConnection(): ICoreServices? {
        return getInstance()?.create(ICoreServices::class.java)
    }




    private fun getInstance(): Retrofit? {
        return Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
                .client(createHttpClient(true))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }


    private fun createHttpClient(redirect: Boolean): OkHttpClient? {

        val httpClient = OkHttpClient.Builder()
        httpClient.followRedirects(redirect)
        httpClient.connectTimeout(40, TimeUnit.SECONDS)
        httpClient.readTimeout(40, TimeUnit.SECONDS)
        httpClient.writeTimeout(40, TimeUnit.SECONDS)
        return httpClient.build()
    }


}
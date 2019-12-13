package com.miklesam.steamapi.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl("https://dota2.com/webapi/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    val RetrofitHolderApi by lazy {
        retrofit.create(dota2Holder::class.java)
    }

}
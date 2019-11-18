package com.miklesam.livedotascore

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Utils{
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl("https://api.steampowered.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }
    private val retrofitRank by lazy{
        Retrofit.Builder()
            .baseUrl("https://dota2.com/webapi/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }


    val SteamHolderApi by lazy {
        retrofit.create(SteamApi::class.java)
    }

    val RankHolderApi by lazy {
        retrofitRank.create(SteamApi::class.java)
    }

}
package com.miklesam.steamapi.retrofit

import com.miklesam.steamapi.datamodels.LiveGames
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface steamHolder {


    @GET("IDOTA2Match_570/GetTopLiveGame/v1/")
    fun getLiveGames(
        @Query("key") key: String,
        @Query("partner") partner: String
        ): Single<LiveGames>

}
package com.miklesam.livedotascore

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SteamApi {

    @GET("IDOTA2Match_570/GetTopLiveGame/v1")
    fun getLiveGames(
        @Query("partner") fields: String,
        @Query("key") sort: String
        ): Single<LiveGame>

}
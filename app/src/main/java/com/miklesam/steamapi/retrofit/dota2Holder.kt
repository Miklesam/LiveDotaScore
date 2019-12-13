package com.miklesam.steamapi.retrofit

import com.miklesam.steamapi.datamodels.RankResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface dota2Holder {

    @GET("ILeaderboard/GetDivisionLeaderboard/v0001/")
    fun getRanks(
        @Query("division") division: String
    ): Single<RankResponse>
}
package com.miklesam.steamapi.ui.tournaments

import androidx.lifecycle.LiveData
import com.miklesam.steamapi.datamodels.League
import com.miklesam.steamapi.retrofit.Dota2ApiClient

class TournamentsRepository {


    fun getTournaments() {
        return Dota2ApiClient.getTournaments()
    }

    fun getLeagues(): LiveData<List<League>> {
        return return Dota2ApiClient.getLeagues()
    }

    fun getErrorLeagues(): LiveData<String> {
        return Dota2ApiClient.getErrorLeagues()
    }
}
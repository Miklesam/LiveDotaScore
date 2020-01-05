package com.miklesam.steamapi.ui.liderboard

import androidx.lifecycle.LiveData
import com.miklesam.steamapi.retrofit.Dota2ApiClient
import com.miklesam.steamapi.datamodels.Player

class LeaderboardRepository {


    fun getLidearBoard(division:String) {
        Dota2ApiClient.getLidearBoard(division)
    }
    fun getPlayers(): LiveData<List<Player>> {
        return Dota2ApiClient.getPlayers()
    }

    fun getErrorPlayers(): LiveData<String> {
        return Dota2ApiClient.getErrorPlayers()
    }

}
package com.miklesam.steamapi.ui.livegames

import androidx.lifecycle.LiveData
import com.miklesam.steamapi.datamodels.LiveGame
import com.miklesam.steamapi.retrofit.SteamApiClient

class LiveGamesRepository{

    fun getLiveGames(){
        SteamApiClient.getLiveGames()
    }

    fun returnGames(): LiveData<List<LiveGame>> {
        return SteamApiClient.returnGames()
    }

}
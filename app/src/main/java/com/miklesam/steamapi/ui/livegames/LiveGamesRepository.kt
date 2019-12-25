package com.miklesam.steamapi.ui.livegames

import androidx.lifecycle.LiveData
import com.miklesam.steamapi.datamodels.LiveGame
import com.miklesam.steamapi.datamodels.LiveLeagueGame
import com.miklesam.steamapi.retrofit.Dota2ApiClient
import com.miklesam.steamapi.retrofit.SteamApiClient

class LiveGamesRepository{

    fun getLiveGames(){
        SteamApiClient.getLiveGames()
    }

    fun getLiveTournamentsGames(league:String){
        SteamApiClient.getTournamentsLiveGames(league)
    }

    fun returnGames(): LiveData<List<LiveGame>> {
        return SteamApiClient.returnGames()
    }

    fun returnCurrentGame(): LiveData<LiveLeagueGame> {
        return SteamApiClient.returnCurrentGame()
    }


    fun getError(): LiveData<String> {
        return SteamApiClient.getError()
    }

}
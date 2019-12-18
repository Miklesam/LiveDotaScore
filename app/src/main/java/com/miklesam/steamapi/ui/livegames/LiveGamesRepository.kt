package com.miklesam.steamapi.ui.livegames

import com.miklesam.steamapi.retrofit.SteamApiClient

class LiveGamesRepository{

    fun getLiveGames(){
        SteamApiClient.getLiveGames()
    }
}
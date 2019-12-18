package com.miklesam.steamapi.ui.livegames

import androidx.lifecycle.ViewModel

class LiveGamesViewModel : ViewModel() {

    private var repository= LiveGamesRepository()

    fun getLiveGames(){
       repository.getLiveGames()
    }


}
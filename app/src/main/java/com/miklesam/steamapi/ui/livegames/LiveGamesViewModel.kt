package com.miklesam.steamapi.ui.livegames

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.miklesam.steamapi.datamodels.LiveGame

class LiveGamesViewModel : ViewModel() {

    private var repository= LiveGamesRepository()

    fun getLiveGames(){
       repository.getLiveGames()
    }

    fun returnGames(): LiveData<List<LiveGame>> {
        return repository.returnGames()
    }


}
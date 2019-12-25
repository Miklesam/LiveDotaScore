package com.miklesam.steamapi.ui.livegames

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miklesam.steamapi.datamodels.LiveGame
import com.miklesam.steamapi.datamodels.LiveLeagueGame

class LiveGamesViewModel : ViewModel() {

    private var repository= LiveGamesRepository()
    private val progress = MutableLiveData<Boolean>()
    fun isProgress(): LiveData<Boolean> = progress

    init {
        progress.value=true
    }

    fun getLiveGames(){
       repository.getLiveGames()
    }
    fun getLiveTournamentGames(league:String){
        repository.getLiveTournamentsGames(league)
    }


    fun returnCurrentGame(): LiveData<LiveLeagueGame> {
       return repository.returnCurrentGame()
    }

    fun returnGames(): LiveData<List<LiveGame>> {
        return repository.returnGames()
    }
    fun setProgress(boolean: Boolean){
        progress.value=boolean
    }
    fun getError(): LiveData<String> {
        return repository.getError()
    }

}
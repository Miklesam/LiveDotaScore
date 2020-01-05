package com.miklesam.steamapi.ui.livegames

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miklesam.steamapi.datamodels.LiveGame
import com.miklesam.steamapi.datamodels.LiveLeagueGame

class LiveGamesViewModel : ViewModel() {

    private var repository= LiveGamesRepository()
    private val progress = MutableLiveData<Boolean>()
    private val currentGame = MutableLiveData<Boolean>()
    fun isProgress(): LiveData<Boolean> = progress
    fun isCurrentGame(): LiveData<Boolean> = currentGame

    init {
        progress.value=true
        currentGame.value=false
    }

    fun getLiveGames(){
       repository.getLiveGames()
    }
    fun getLiveTournamentGames(league:Int){
        repository.getLiveTournamentsGames(league)
    }
    fun setCurrentGame(boolean: Boolean){
        currentGame.value=boolean
        Log.w("Set Current", boolean.toString())
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
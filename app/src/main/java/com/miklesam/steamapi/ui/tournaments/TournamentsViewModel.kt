package com.miklesam.steamapi.ui.tournaments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miklesam.steamapi.datamodels.League

class TournamentsViewModel : ViewModel() {

    private var repository= TournamentsRepository()
    private val progress = MutableLiveData<Boolean>()
    fun isProgress(): LiveData<Boolean> = progress

    init{
        getTournaments()
    }


    fun getTournaments(){
        return repository.getTournaments()
    }

    fun getLeagues(): LiveData<List<League>> {
        return repository.getLeagues()
    }

    fun setProgress(boolean: Boolean){
        progress.value=boolean
    }
    fun getErrorLeagues(): LiveData<String> {
        return repository.getErrorLeagues()
    }
}
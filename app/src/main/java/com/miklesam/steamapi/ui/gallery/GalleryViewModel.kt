package com.miklesam.steamapi.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miklesam.steamapi.datamodels.Player

class GalleryViewModel : ViewModel() {

    private var repository= GalleryRepository()
    private val progress = MutableLiveData<Boolean>()
    private val division = MutableLiveData<String>()
    fun isProgress(): LiveData<Boolean> = progress

    init {
        division.value=""
    }

    fun getPlayers(): LiveData<List<Player>> {
        return repository.getPlayers()
    }

    fun setProgress(boolean: Boolean){
        progress.value=boolean
    }

    fun setDevision(divisionRank: String){
        if(!divisionRank.equals(division.value)){
            progress.value=true
            division.value=divisionRank
            repository.getLidearBoard(divisionRank)
        }
    }

    fun getErrorPlayers(): LiveData<String> {
        return repository.getErrorPlayers()
    }

}
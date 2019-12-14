package com.miklesam.steamapi.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miklesam.steamapi.datamodels.League
import com.miklesam.steamapi.datamodels.Player

class SlideshowViewModel : ViewModel() {

    private var repository= SlideshowRepository()
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
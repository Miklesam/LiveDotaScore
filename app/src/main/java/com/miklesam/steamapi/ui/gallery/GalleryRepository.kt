package com.miklesam.steamapi.ui.gallery

import androidx.lifecycle.LiveData
import com.miklesam.steamapi.retrofit.Dota2ApiClient
import com.miklesam.steamapi.datamodels.Player

class GalleryRepository {


    fun getLidearBoard(division:String) {
        Dota2ApiClient.getLidearBoard(division)
    }
    fun getPlayers(): LiveData<List<Player>> {
        return Dota2ApiClient.getPlayers()
    }

}
package com.miklesam.steamapi.ui.slideshow

import androidx.lifecycle.LiveData
import com.miklesam.steamapi.datamodels.League
import com.miklesam.steamapi.retrofit.Dota2ApiClient
import com.miklesam.steamapi.ui.gallery.GalleryRepository

class SlideshowRepository {


    fun getTournaments() {
        return Dota2ApiClient.getTournaments()
    }

    fun getLeagues(): LiveData<List<League>> {
        return return Dota2ApiClient.getLeagues()
    }

    fun getErrorLeagues(): LiveData<String> {
        return Dota2ApiClient.getErrorLeagues()
    }
}
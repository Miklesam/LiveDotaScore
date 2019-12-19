package com.miklesam.steamapi.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miklesam.steamapi.datamodels.LiveGame
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

object SteamApiClient{
    val mGames = MutableLiveData<List<LiveGame>>()

    fun returnGames(): LiveData<List<LiveGame>> {
        return mGames
    }



    fun getLiveGames(){
        mGames.value=null
        val compositeDisposable= CompositeDisposable()
        compositeDisposable.add(
            ServiceGenerator.SteamHolderApi.getLiveGames("DC5456E165A004A2F31197712AA3990D","0")
                .timeout(4, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mGames.value=it.game_list
                    //Log.w("liveGames",it.game_list.get(0).dire_score)
                    //Log.w("liveGames",it.game_list.get(0).radiant_score)
                    //Log.w("liveGames",it.game_list.get(0).league_id)

                },{
                    Log.w("liveGames","Error: "+it.message)
                }))
    }
}
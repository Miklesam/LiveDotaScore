package com.miklesam.steamapi.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miklesam.steamapi.datamodels.Player
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

object Dota2ApiClient {
    val mPlayers = MutableLiveData<List<Player>>()

    fun getPlayers(): LiveData<List<Player>> {
        return mPlayers
    }

    fun getLidearBoard(division:String){
        val compositeDisposable= CompositeDisposable()
        compositeDisposable.add(
            ServiceGenerator.RetrofitHolderApi.getRanks(division)
                .timeout(4, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mPlayers.value=it.leaderboard
                    //Log.w("Get It",  it.leaderboard.get(0).name)
                },{
                    Log.w("Get It",  it.message)
                }))
    }
}
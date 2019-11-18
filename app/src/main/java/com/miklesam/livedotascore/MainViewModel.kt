package com.miklesam.livedotascore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miklesam.livedotascore.MainActivity.Companion.rankList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainViewModel :ViewModel(){

    private val contentLiveData = MutableLiveData<String>()
    private val loadingState = MutableLiveData<Boolean>()
    private val contentError = MutableLiveData<String>()

    fun getContent(): LiveData<String> = contentLiveData
    fun getError(): LiveData<String> = contentError
    fun getLoadingState(): LiveData<Boolean> = loadingState

    fun getLiveGames(){
        Log.w("RxallListMapinFr", "IngetLiveGame")
        loadingState.value = true
        val compositeDisposable= CompositeDisposable()
        compositeDisposable.add(
            Utils.SteamHolderApi.getLiveGames("0","0E91C24690F9EA6870E4D18C133A6C0A")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    var games=it.getGame()
                    if (games != null) {
                        for(game in games){
                            Log.w("Rx", game.match_id)
                            Log.w("Rx", game.league_id)
                        }
                    }
                    loadingState.value = false
                  },{
                    compositeDisposable.dispose()
                    Log.w("RxError",it.message)
                    showError(it.message!!)

                }))

    }

    fun getRanks(){
        Log.w("RxallListMapinFr", "getRanks")
        loadingState.value = true
        val compositeDisposable= CompositeDisposable()
        compositeDisposable.add(
            Utils.RankHolderApi.getRanks("europe")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    rankList.clear()
                    for(player in it.getGame()!!){
                        if(!player.team_tag.equals("")){
                            rankList.add(player.rank+"."+ player.team_tag+"."+player.name)
                        }else{
                            rankList.add(player.rank+"."+player.name)
                        }

                    }

                    loadingState.value = false
                    contentLiveData.value="1"
                },{
                    compositeDisposable.dispose()
                    Log.w("RxError",it.message)
                    showError(it.message!!)
                }))
    }

    fun showError(error:String){
        loadingState.value=false
        contentError.value=error
    }

}
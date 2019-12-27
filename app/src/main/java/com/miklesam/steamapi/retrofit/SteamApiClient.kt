package com.miklesam.steamapi.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miklesam.steamapi.datamodels.LiveGame
import com.miklesam.steamapi.datamodels.LiveLeagueGame
import com.miklesam.steamapi.datamodels.LivePlayer
import com.miklesam.steamapi.utils.Constants.DEFAULT_HEROES_NAME
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

object SteamApiClient{
    val mGames = MutableLiveData<List<LiveGame>>()
    val mGame = MutableLiveData<LiveLeagueGame>()
    val mError = MutableLiveData<String>()

    fun returnGames(): LiveData<List<LiveGame>> {
        return mGames
    }

    fun returnCurrentGame(): LiveData<LiveLeagueGame> {
        return mGame
    }



    fun getError(): LiveData<String> {
        return mError
    }

    fun getLiveGames(){
        mGames.value=null
        mError.value=null
        val compositeDisposable= CompositeDisposable()
        compositeDisposable.add(
            ServiceGenerator.SteamHolderApi.getLiveGames("DC5456E165A004A2F31197712AA3990D","0")
                //.delay(5,TimeUnit.SECONDS)
                .timeout(8, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mGames.value=it.game_list
                    //Log.w("liveGames",it.game_list.get(0).dire_score)
                    //Log.w("liveGames",it.game_list.get(0).radiant_score)
                    //Log.w("liveGames",it.game_list.get(0).league_id)

                },{
                    mError.value=it.message
                    //Log.w("liveGames","Error: "+it.message)
                }))
    }


    fun getTournamentsLiveGames(league:String){
        mGame.value=null
        val radiantTeam=ArrayList<LivePlayer>()
        val direTeam=ArrayList<LivePlayer>()
        val compositeDisposable= CompositeDisposable()
        radiantTeam.clear()
        direTeam.clear()
        compositeDisposable.add(
            ServiceGenerator.SteamHolderApi.getLiveLeagueGames("DC5456E165A004A2F31197712AA3990D",league)
                //.timeout(4, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it.result.games.size==0){
                        Log.w("liveGames","Error: Seems like game already end")
                    }else{
                        mGame.value=it.result.games.get(0)
                    }
                    },{
                    Log.w("liveGames","Error: "+it.message)
                }))
    }

}
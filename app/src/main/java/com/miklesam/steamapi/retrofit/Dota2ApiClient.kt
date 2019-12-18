package com.miklesam.steamapi.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miklesam.steamapi.datamodels.League
import com.miklesam.steamapi.datamodels.Player
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

object Dota2ApiClient {
    val mPlayers = MutableLiveData<List<Player>>()
    val mLeagues = MutableLiveData<List<League>>()
    val mPlayersError = MutableLiveData<String>()
    val mLeagueError = MutableLiveData<String>()

    fun getPlayers(): LiveData<List<Player>> {
        return mPlayers
    }

    fun getErrorPlayers(): LiveData<String> {
        return mPlayersError
    }

    fun getErrorLeagues(): LiveData<String> {
        return mLeagueError
    }

    fun getLeagues(): LiveData<List<League>> {
        return mLeagues
    }

    fun getLidearBoard(division:String){
        mPlayers.value=null
        mPlayersError.value=null
        val compositeDisposable= CompositeDisposable()
        compositeDisposable.add(
            ServiceGenerator.RetrofitHolderApi.getRanks(division)
                .timeout(4, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mPlayers.value=it.leaderboard
                },{
                    if(it is TimeoutException){
                        mPlayersError.postValue("action doesn't complete within the given time");
                        compositeDisposable.dispose()
                    }else{
                    mPlayersError.value=it.message
                }

                }))
    }



    fun getTournaments(){
        mLeagues.value=null
        mLeagueError.value=null
        val compositeDisposable= CompositeDisposable()
        compositeDisposable.add(
            ServiceGenerator.RetrofitHolderApi.getTournaments()
                .timeout(4, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val teir4=ArrayList<League>()
                    teir4.clear()
                    for (league in it.infos){
                        if( league.tier.toInt()>2&&league.tier.toInt()<6){
                            teir4.add(league)
                        }
                    }
                    val teirnew=teir4.asReversed()
                    mLeagues.postValue(teirnew)
                },{
                    if(it is TimeoutException){
                        mLeagueError.postValue("action doesn't complete within the given time");
                        compositeDisposable.dispose()
                    }else{
                        mLeagueError.value=it.message
                    }

                }))
    }
}
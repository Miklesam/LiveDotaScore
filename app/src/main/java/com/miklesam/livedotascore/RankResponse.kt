package com.miklesam.livedotascore

class RankResponse (){
    private val leaderboard: List<Player>? = null

    fun getGame(): List<Player>? {
        return leaderboard
    }
}
package com.miklesam.steamapi.datamodels

data class LivePlayer(
    //var name:String,
    var hero_id:Int,
    var level:String,
    var net_worth:String,
    var account_id:String,
    var kills:Int,
    var death:Int,
    var assists:Int,
    var last_hits:Int,
    var denies:Int,
    var gold_per_min:Int,
    var xp_per_min:Int
    //var team:Int
)
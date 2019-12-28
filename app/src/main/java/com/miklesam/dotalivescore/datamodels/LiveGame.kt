package com.miklesam.steamapi.datamodels

data class LiveGame(
    var league_id:String,
    var radiant_score:String,
    var dire_score:String,
    var team_name_radiant:String,
    var team_name_dire:String,
    var radiant_lead:String,
    var game_time:String,
    var average_mmr:String



)
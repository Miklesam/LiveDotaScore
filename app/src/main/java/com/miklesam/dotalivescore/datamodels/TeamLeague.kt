package com.miklesam.steamapi.datamodels

import com.miklesam.dotalivescore.datamodels.Hero_id

data class TeamLeague(var score:String, var players:List<LivePlayer>,var picks:List<Hero_id>)
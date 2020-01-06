package com.miklesam.steamapi.datamodels

import com.miklesam.dotalivescore.datamodels.Team

data class LiveLeagueGame(var match_id:Long, var scoreboard: Scoreboard,var radiant_team: Team, var dire_team: Team)
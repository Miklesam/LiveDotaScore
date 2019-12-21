package com.miklesam.steamapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miklesam.steamapi.R
import com.miklesam.steamapi.datamodels.LiveGame
import kotlinx.android.synthetic.main.lidearboard_item.view.*
import kotlinx.android.synthetic.main.livegameitem.view.*

class LiveGamesAdapter(onGameListener: OnGameListener) : RecyclerView.Adapter<LiveGamesAdapter.GameHolder>() {

    private var mGames:List<LiveGame> = ArrayList()
    private val mOnGameListener=onGameListener


    fun setGames(games:List<LiveGame>){
        mGames=games
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.livegameitem,parent,false);
        return GameHolder(itemView,mOnGameListener)
    }

    override fun getItemCount(): Int {
        return mGames.size
    }

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        val currentGame: LiveGame = mGames.get(position)
        holder.radiantScore.text=currentGame.radiant_score
        holder.direScore.text=currentGame.dire_score
        holder.lead.text=currentGame.radiant_lead
        if(currentGame.league_id.equals("0")){
            val averageMMR="average mmr: ${currentGame.average_mmr}"
            holder.leagueId.text=averageMMR
        }else{
            holder.leagueId.text=currentGame.league_id
        }

        holder.radiantName.text=currentGame.team_name_radiant
        holder.direName.text=currentGame.team_name_dire
        val currentTime=currentGame.game_time.toInt()
        val currentMin=currentTime/60
        val currentSec=currentTime%60
        val minAndSec:String
        if(currentSec<10){
            minAndSec="time:$currentMin:0$currentSec"
        }else{
            minAndSec="time:$currentMin:$currentSec"
        }


        holder.gameTime.text=minAndSec
    }

    class GameHolder(itemView: View,var gameListener: OnGameListener): RecyclerView.ViewHolder(itemView),View.OnClickListener{
        override fun onClick(p0: View?) {
            gameListener.onGameClick(adapterPosition)
        }

        var radiantScore: TextView
        var direScore: TextView
        var lead: TextView
        var leagueId:TextView
        var radiantName:TextView
        var direName:TextView
        var gameTime:TextView
        init {
            radiantScore=itemView.radiantScore
            direScore=itemView.direScore
            lead=itemView.lead
            leagueId=itemView.leagueId
            radiantName=itemView.radiantName
            direName=itemView.direName
            gameTime=itemView.gameTime
            itemView.setOnClickListener(this)
        }
    }
}
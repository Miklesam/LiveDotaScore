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

class LiveGamesAdapter : RecyclerView.Adapter<LiveGamesAdapter.GameHolder>() {

    private var mGames:List<LiveGame> = ArrayList()


    fun setGames(games:List<LiveGame>){
        mGames=games
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.livegameitem,parent,false);
        return GameHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mGames.size
    }

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        val currentGame: LiveGame = mGames.get(position)
        holder.radiantScore.text=currentGame.radiant_score
        holder.direScore.text=currentGame.dire_score
        holder.lead.text=currentGame.radiant_lead
        holder.leagueId.text=currentGame.league_id
        holder.radiantName.text=currentGame.team_name_radiant
        holder.direName.text=currentGame.team_name_dire
    }

    class GameHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var radiantScore: TextView
        var direScore: TextView
        var lead: TextView
        var leagueId:TextView
        var radiantName:TextView
        var direName:TextView
        init {
            radiantScore=itemView.radiantScore
            direScore=itemView.direScore
            lead=itemView.lead
            leagueId=itemView.leagueId
            radiantName=itemView.radiantName
            direName=itemView.direName
        }
    }
}
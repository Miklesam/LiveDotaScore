package com.miklesam.steamapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miklesam.dotalivescore.R
import com.miklesam.dotalivescore.my_ui.tournaments.TournamentsFragment.Companion.mapa
import com.miklesam.steamapi.datamodels.LiveGame
import kotlinx.android.synthetic.main.livegameitem.view.*

class LiveGamesAdapter(onGameListener: OnGameListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mGames:List<LiveGame> = ArrayList()
    private val mOnGameListener=onGameListener
    companion object{
        const val LIVEGAME_TYPE = 0
        const val NOGAME_TYPE = 1
    }


    fun setGames(games:List<LiveGame>){
        mGames=games
        notifyDataSetChanged()
    }

    fun setNoGame(){
        val asd=LiveGame(0,"","NoLiveGame","","","","","")
        setGames(listOf(asd))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view :View
        return when(viewType){
            NOGAME_TYPE->{
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layuot_datas_end, parent, false)
                return EndDataViewHolder(view)
            }else->{
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.livegameitem,parent,false);
                return GameHolder(view,mOnGameListener)
            }
        }



    }

    override fun getItemCount(): Int {
        return mGames.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val itemViewType = getItemViewType(position)
        var myHolder =holder
        when(itemViewType){
            NOGAME_TYPE->{
                holder as EndDataViewHolder

            } else->{
            holder as GameHolder
            val currentGame: LiveGame = mGames.get(position)
            holder.radiantScore.text=currentGame.radiant_score
            holder.direScore.text=currentGame.dire_score
            holder.lead.text=currentGame.radiant_lead
            if(currentGame.league_id.equals("0")){
                val averageMMR="average mmr: ${currentGame.average_mmr}"
                holder.leagueId.text=averageMMR
            }else{
                holder.leagueId.text=mapa.get(currentGame.league_id.toString())
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
        }

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
    class EndDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        if (mGames.get(0).dire_score.equals("NoLiveGame")){
            return NOGAME_TYPE
        }else{
            return LIVEGAME_TYPE
        }
    }
}
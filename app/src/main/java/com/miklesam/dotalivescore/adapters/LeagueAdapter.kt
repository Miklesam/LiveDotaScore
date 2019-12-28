package com.miklesam.steamapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miklesam.dotalivescore.R
import com.miklesam.steamapi.datamodels.League
import com.miklesam.steamapi.datamodels.Player
import kotlinx.android.synthetic.main.league_item.view.*

class LeagueAdapter: RecyclerView.Adapter<LeagueAdapter.LeagueHolder>() {

    private var mLeagues:List<League> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.league_item,parent,false);
        return LeagueHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mLeagues.size
    }

    fun setLeagues(leagues:List<League>){
        mLeagues=leagues
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: LeagueHolder, position: Int) {
        val currentLeague: League = mLeagues.get(position)
        holder.leagueName.text=currentLeague.name
        holder.tier.text=currentLeague.tier
    }


    class LeagueHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tier: TextView
        var leagueName: TextView
        init {
            leagueName=itemView.leagueName
            tier=itemView.leagueTier
        }
    }
}
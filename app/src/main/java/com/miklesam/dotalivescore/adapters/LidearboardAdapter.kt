package com.miklesam.steamapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miklesam.dotalivescore.R
import com.miklesam.steamapi.datamodels.Player
import kotlinx.android.synthetic.main.lidearboard_item.view.*

class LidearboardAdapter: RecyclerView.Adapter<LidearboardAdapter.PlayerHolder>() {

    private var mPlayers:List<Player> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.lidearboard_item,parent,false);
        return PlayerHolder(itemView)
    }

    fun setPlayers(players:List<Player>){
        mPlayers=players
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return mPlayers.size
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        val currentPlayer: Player = mPlayers.get(position)
        holder.playerName.text=currentPlayer.name
        holder.rank.text=currentPlayer.rank
        holder.tag.text=currentPlayer.team_tag
    }

    class PlayerHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var rank: TextView
        var playerName: TextView
        var tag: TextView
        init {
            playerName=itemView.playerName
            rank=itemView.playerRank
            tag=itemView.playerTag
        }
    }
}



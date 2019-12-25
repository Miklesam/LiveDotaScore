package com.miklesam.steamapi.ui.livegames

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.miklesam.steamapi.R
import com.miklesam.steamapi.adapters.LiveGamesAdapter
import com.miklesam.steamapi.adapters.OnGameListener
import com.miklesam.steamapi.datamodels.LiveGame
import com.miklesam.steamapi.utils.Constants
import com.miklesam.steamapi.utils.Constants.DEFAULT_HEROES_IMAGE

class LiveGamesFragment : Fragment(),OnGameListener {
    lateinit var mGames:List<LiveGame>
    override fun onGameClick(position: Int) {
        Log.w("Click, In Fragment","position= ${mGames.get(position).team_name_dire}"
        )
        if(mGames.get(position).league_id.equals("0")){
            Toast.makeText(context, "This is Pub please choose league game", Toast.LENGTH_SHORT).show()
        }else{
            liveGamesViewModel.getLiveTournamentGames(mGames.get(position).league_id)
            swiperRefresh.visibility=GONE
            gameInfo.visibility=VISIBLE
        }

    }

    private lateinit var liveGamesViewModel: LiveGamesViewModel
    private lateinit var gameInfo: LinearLayout
    private lateinit var swiperRefresh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        liveGamesViewModel =
            ViewModelProviders.of(this).get(LiveGamesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_livegames, container, false)
        val recyclerLiveGame=root.findViewById<RecyclerView>(R.id.recyclerLiveGames)
        val progressBar=root.findViewById<ProgressBar>(R.id.progress)
        val errorText=root.findViewById<TextView>(R.id.errorText)
        recyclerLiveGame.layoutManager = LinearLayoutManager(context)
        recyclerLiveGame.setHasFixedSize(true)
        val adapter = LiveGamesAdapter(this)
        recyclerLiveGame.adapter = adapter
        swiperRefresh=root.findViewById<SwipeRefreshLayout>(R.id.swiperRefresh)
        gameInfo=root.findViewById<LinearLayout>(R.id.gameInfo)
        val matchId=root.findViewById<TextView>(R.id.matchId)
        val Radiant1=root.findViewById<TextView>(R.id.Radiant1)
        val Radiant2=root.findViewById<TextView>(R.id.Radiant2)
        val Radiant3=root.findViewById<TextView>(R.id.Radiant3)
        val Radiant4=root.findViewById<TextView>(R.id.Radiant4)
        val Radiant5=root.findViewById<TextView>(R.id.Radiant5)

        val Dire1=root.findViewById<TextView>(R.id.Dire1)

        val RadiantImage1=root.findViewById<ImageView>(R.id.radIma1)
        val RadiantImage2=root.findViewById<ImageView>(R.id.radIma2)
        val RadiantImage3=root.findViewById<ImageView>(R.id.radIma3)
        val RadiantImage4=root.findViewById<ImageView>(R.id.radIma4)
        val RadiantImage5=root.findViewById<ImageView>(R.id.radIma5)

        val DireImage1=root.findViewById<ImageView>(R.id.direIma1)

        liveGamesViewModel.getLiveGames()
        liveGamesViewModel.setProgress(true)

        liveGamesViewModel.returnGames().observe(this, Observer {
            if(it!=null){
                //Log.w("InFragment ", it.size.toString())
                mGames=it
                adapter.setGames(it)
                swiperRefresh.isRefreshing=false
                liveGamesViewModel.setProgress(false)
            }

        })


        liveGamesViewModel.isProgress().observe(this, Observer {
            if(it){
                errorText.visibility= View.GONE
                progressBar.visibility= View.VISIBLE
                recyclerLiveGame.visibility= View.GONE
            }else{
                progressBar.visibility= View.GONE
                recyclerLiveGame.visibility= View.VISIBLE
            }
        })

        liveGamesViewModel.getError().observe(this, Observer {
            if(it!=null){
                liveGamesViewModel.setProgress(false)
                errorText.text=it
                errorText.visibility= View.VISIBLE
                recyclerLiveGame.visibility= View.GONE
            }
        })

        liveGamesViewModel.returnCurrentGame().observe(this, Observer {
            if(it!=null){
                Log.w("In Fragment get Match",it.match_id)
                matchId.text=it.match_id
                Radiant1.text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.radiant.players.get(0).hero_id)
                Radiant2.text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.radiant.players.get(1).hero_id)
                Radiant3.text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.radiant.players.get(2).hero_id)
                Radiant4.text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.radiant.players.get(3).hero_id)
                Radiant5.text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.radiant.players.get(4).hero_id)

                RadiantImage1.setImageResource(DEFAULT_HEROES_IMAGE[it.scoreboard.radiant.players.get(0).hero_id] ?: error(""))
                RadiantImage2.setImageResource(DEFAULT_HEROES_IMAGE.get(it.scoreboard.radiant.players.get(1).hero_id)!!)
                RadiantImage3.setImageResource(DEFAULT_HEROES_IMAGE.get(it.scoreboard.radiant.players.get(2).hero_id)!!)
                RadiantImage4.setImageResource(DEFAULT_HEROES_IMAGE.get(it.scoreboard.radiant.players.get(3).hero_id)!!)
                RadiantImage5.setImageResource(DEFAULT_HEROES_IMAGE.get(it.scoreboard.radiant.players.get(4).hero_id)!!)

                Dire1.text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.dire.players.get(0).hero_id)
                DireImage1.setImageResource(DEFAULT_HEROES_IMAGE.get(it.scoreboard.dire.players.get(0).hero_id)!!)

            }
        })

        swiperRefresh.setOnRefreshListener {
            Log.w("Refresh","New Refresh")
            liveGamesViewModel.getLiveGames() }



        return root
    }
}
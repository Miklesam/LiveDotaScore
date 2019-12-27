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
    lateinit  var liveGamesViewModel: LiveGamesViewModel
    private lateinit var gameInfo: LinearLayout
    private lateinit var swiperRefresh: SwipeRefreshLayout


    companion object{
        var currentGame=0
    }


    lateinit var mGames:List<LiveGame>
    override fun onGameClick(position: Int) {
        Log.w("Click, In Fragment","position= ${mGames.get(position).team_name_dire}"
        )
        if(mGames.get(position).league_id.equals("0")){
            Toast.makeText(context, "This is Pub please choose league game", Toast.LENGTH_SHORT).show()
        }else{
            currentGame=1
            liveGamesViewModel.getLiveTournamentGames(mGames.get(position).league_id)
            liveGamesViewModel.setCurrentGame(true)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        liveGamesViewModel =
            ViewModelProviders.of(this).get(LiveGamesViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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
        val Dire2=root.findViewById<TextView>(R.id.Dire2)
        val Dire3=root.findViewById<TextView>(R.id.Dire3)
        val Dire4=root.findViewById<TextView>(R.id.Dire4)
        val Dire5=root.findViewById<TextView>(R.id.Dire5)

        val RadiantImage1=root.findViewById<ImageView>(R.id.radIma1)
        val RadiantImage2=root.findViewById<ImageView>(R.id.radIma2)
        val RadiantImage3=root.findViewById<ImageView>(R.id.radIma3)
        val RadiantImage4=root.findViewById<ImageView>(R.id.radIma4)
        val RadiantImage5=root.findViewById<ImageView>(R.id.radIma5)

        val DireImage1=root.findViewById<ImageView>(R.id.direIma1)
        val DireImage2=root.findViewById<ImageView>(R.id.direIma2)
        val DireImage3=root.findViewById<ImageView>(R.id.direIma3)
        val DireImage4=root.findViewById<ImageView>(R.id.direIma4)
        val DireImage5=root.findViewById<ImageView>(R.id.direIma5)

        val firstStat=root.findViewById<View>(R.id.test1)
        val secondStat=root.findViewById<View>(R.id.test2)

        val radKDA1=firstStat.findViewById<TextView>(R.id.radKDA)
        val radKDA2=secondStat.findViewById<TextView>(R.id.radKDA)


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

                if(it.scoreboard.radiant.players.get(0).hero_id>0){
                    matchId.text=it.match_id
                    Radiant1.text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.radiant.players.get(0).hero_id)
                    Radiant2.text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.radiant.players.get(1).hero_id)
                    Radiant3.text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.radiant.players.get(2).hero_id)
                    Radiant4.text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.radiant.players.get(3).hero_id)
                    Radiant5.text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.radiant.players.get(4).hero_id)

                    RadiantImage1.setImageResource(DEFAULT_HEROES_IMAGE[it.scoreboard.radiant.players.get(0).hero_id] ?: error("non image"))
                    RadiantImage2.setImageResource(DEFAULT_HEROES_IMAGE[it.scoreboard.radiant.players.get(1).hero_id] ?: error("non image"))
                    RadiantImage3.setImageResource(DEFAULT_HEROES_IMAGE[it.scoreboard.radiant.players.get(2).hero_id] ?: error("non image"))
                    RadiantImage4.setImageResource(DEFAULT_HEROES_IMAGE[it.scoreboard.radiant.players.get(3).hero_id] ?: error("non image"))
                    RadiantImage5.setImageResource(DEFAULT_HEROES_IMAGE[it.scoreboard.radiant.players.get(4).hero_id] ?: error("non image"))

                    Dire1.text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.dire.players.get(0).hero_id)
                    Dire2.text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.dire.players.get(1).hero_id)
                    Dire3.text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.dire.players.get(2).hero_id)
                    Dire4.text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.dire.players.get(3).hero_id)
                    Dire5.text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.dire.players.get(4).hero_id)

                    DireImage1.setImageResource(DEFAULT_HEROES_IMAGE[it.scoreboard.dire.players.get(0).hero_id] ?: error("non image"))
                    DireImage2.setImageResource(DEFAULT_HEROES_IMAGE[it.scoreboard.dire.players.get(1).hero_id] ?: error("non image"))
                    DireImage3.setImageResource(DEFAULT_HEROES_IMAGE[it.scoreboard.dire.players.get(2).hero_id] ?: error("non image"))
                    DireImage4.setImageResource(DEFAULT_HEROES_IMAGE[it.scoreboard.dire.players.get(3).hero_id] ?: error("non image"))
                    DireImage5.setImageResource(DEFAULT_HEROES_IMAGE[it.scoreboard.dire.players.get(4).hero_id] ?: error("non image"))
                    val KDA=it.scoreboard.dire.players.get(0).kills.toString()+"/"+it.scoreboard.dire.players.get(0).death.toString()+"/"+it.scoreboard.dire.players.get(0).assists.toString()
                    radKDA1.text=KDA
                    radKDA2.text="15/1/10"

                } else{
                    matchId.text="Пики Баны"
                }


            }
        })


        liveGamesViewModel.isCurrentGame().observe(this, Observer {
            if(it){
                swiperRefresh.visibility=GONE
                gameInfo.visibility=VISIBLE
            }else{
                swiperRefresh.visibility= VISIBLE
                gameInfo.visibility=GONE
            }
        })

        swiperRefresh.setOnRefreshListener {
            Log.w("Refresh","New Refresh")
            liveGamesViewModel.getLiveGames() }





        return root
    }

    override fun onDestroyView() {
        currentGame=0
        super.onDestroyView()
    }



}
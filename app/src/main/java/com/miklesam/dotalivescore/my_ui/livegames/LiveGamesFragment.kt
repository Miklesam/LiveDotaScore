package com.miklesam.dotalivescore.my_ui.livegames

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.miklesam.dotalivescore.R
import com.miklesam.steamapi.adapters.LiveGamesAdapter
import com.miklesam.steamapi.adapters.OnGameListener
import com.miklesam.steamapi.datamodels.LiveGame
import com.miklesam.steamapi.ui.livegames.LiveGamesViewModel
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
        val radTeam=root.findViewById<TextView>(R.id.radTeam)
        val direTeam=root.findViewById<TextView>(R.id.DireTeam)
        recyclerLiveGame.adapter = adapter
        swiperRefresh=root.findViewById<SwipeRefreshLayout>(R.id.swiperRefresh)
        gameInfo=root.findViewById<LinearLayout>(R.id.gameInfo)

        val matchId=root.findViewById<TextView>(R.id.matchId)
        val RadHero = Array<TextView>(5){root.findViewById<TextView>(R.id.Radiant1)
        }
        RadHero[1]=root.findViewById<TextView>(R.id.Radiant2)
        RadHero[2]=root.findViewById<TextView>(R.id.Radiant3)
        RadHero[3]=root.findViewById<TextView>(R.id.Radiant4)
        RadHero[4]=root.findViewById<TextView>(R.id.Radiant5)
        val DireHero = Array(5){root.findViewById<TextView>(R.id.Dire1)}
        DireHero[1]=root.findViewById<TextView>(R.id.Dire2)
        DireHero[2]=root.findViewById<TextView>(R.id.Dire3)
        DireHero[3]=root.findViewById<TextView>(R.id.Dire4)
        DireHero[4]=root.findViewById<TextView>(R.id.Dire5)
        val RadImage = Array(5) {root.findViewById<ImageView>(R.id.radIma1)}
        RadImage[1]=root.findViewById<ImageView>(R.id.radIma2)
        RadImage[2]=root.findViewById<ImageView>(R.id.radIma3)
        RadImage[3]=root.findViewById<ImageView>(R.id.radIma4)
        RadImage[4]=root.findViewById<ImageView>(R.id.radIma5)
        val DireImage = Array(5) {root.findViewById<ImageView>(R.id.direIma1)}
        DireImage[1]=root.findViewById<ImageView>(R.id.direIma2)
        DireImage[2]=root.findViewById<ImageView>(R.id.direIma3)
        DireImage[3]=root.findViewById<ImageView>(R.id.direIma4)
        DireImage[4]=root.findViewById<ImageView>(R.id.direIma5)
        val includeRoot = Array(5) {root.findViewById<View>(R.id.test1)}
        includeRoot[1]=root.findViewById<View>(R.id.test2)
        includeRoot[2]=root.findViewById<View>(R.id.test3)
        includeRoot[3]=root.findViewById<View>(R.id.test4)
        includeRoot[4]=root.findViewById<View>(R.id.test5)

        val radKDA = Array(5) {includeRoot[0].findViewById<TextView>(R.id.radKDA)}
        val direKDA = Array(5) {includeRoot[0].findViewById<TextView>(R.id.direKDA)}
        val radLH = Array(5) {includeRoot[0].findViewById<TextView>(R.id.radLHDN)}
        val direLH = Array(5) {includeRoot[0].findViewById<TextView>(R.id.direLHDN)}
        val radGPM = Array(5) {includeRoot[0].findViewById<TextView>(R.id.radGPM)}
        val direGPM = Array(5) {includeRoot[0].findViewById<TextView>(R.id.direGPM)}

        for (i in 0..4){
            radKDA[i]=includeRoot[i].findViewById<TextView>(R.id.radKDA)
            direKDA[i]=includeRoot[i].findViewById<TextView>(R.id.direKDA)
            radLH[i]=includeRoot[i].findViewById<TextView>(R.id.radLHDN)
            radGPM[i]=includeRoot[i].findViewById<TextView>(R.id.radGPM)
            direLH[i]=includeRoot[i].findViewById<TextView>(R.id.direLHDN)
            direGPM[i]=includeRoot[i].findViewById<TextView>(R.id.direGPM)
        }

        val emo=root.findViewById<ImageView>(R.id.emo)
        emo.setOnClickListener { liveGamesViewModel.setCurrentGame(false) }

        liveGamesViewModel.getLiveGames()
        liveGamesViewModel.setProgress(true)


        liveGamesViewModel.returnGames().observe(this, Observer {
            if(it!=null){
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
                if(it.scoreboard.radiant.players.get(0).hero_id>0){
                    matchId.text=it.match_id
                    radTeam.text=it.radiant_team.team_name
                    direTeam.text=it.dire_team.team_name

                    for (i in 0..4){
                        RadHero[i].text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.radiant.players.get(i).hero_id)
                        DireHero[i].text=Constants.DEFAULT_HEROES_NAME.get(it.scoreboard.dire.players.get(i).hero_id)
                        RadImage[i].setImageResource(DEFAULT_HEROES_IMAGE[it.scoreboard.radiant.players.get(i).hero_id] ?: error("non image"))
                        DireImage[i].setImageResource(DEFAULT_HEROES_IMAGE[it.scoreboard.dire.players.get(i).hero_id] ?: error("non image"))
                        val KDA=it.scoreboard.radiant.players.get(i).kills.toString()+"/"+it.scoreboard.radiant.players.get(i).death.toString()+"/"+it.scoreboard.radiant.players.get(i).assists.toString()
                        radKDA[i].text=KDA
                        val LH=it.scoreboard.radiant.players.get(i).last_hits.toString()+"/"+it.scoreboard.radiant.players.get(i).denies.toString()
                        radLH[i].text=LH
                        val GPM=it.scoreboard.radiant.players.get(i).gold_per_min.toString()+"/"+it.scoreboard.radiant.players.get(i).xp_per_min.toString()
                        radGPM[i].text=GPM
                        val DireKDA=it.scoreboard.dire.players.get(i).kills.toString()+"/"+it.scoreboard.dire.players.get(i).death.toString()+"/"+it.scoreboard.dire.players.get(i).assists.toString()
                        direKDA[i].text=DireKDA
                        val DireLH=it.scoreboard.dire.players.get(i).last_hits.toString()+"/"+it.scoreboard.dire.players.get(i).denies.toString()
                        direLH[i].text=DireLH
                        val DireGPM=it.scoreboard.dire.players.get(i).gold_per_min.toString()+"/"+it.scoreboard.dire.players.get(i).xp_per_min.toString()
                        direGPM[i].text=DireGPM
                    }



                } else{
                    matchId.text="Пики Баны"
                }


            }
        })


        liveGamesViewModel.isCurrentGame().observe(this, Observer {
            if(it){
                swiperRefresh.visibility=GONE
                gameInfo.visibility=VISIBLE
                emo.visibility= VISIBLE
            }else{
                swiperRefresh.visibility= VISIBLE
                gameInfo.visibility=GONE
                emo.visibility= INVISIBLE
                //Radiant1.text=""
                //RadiantImage1.setImageResource(android.R.color.transparent);
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
package com.miklesam.dotalivescore.my_ui.tournaments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miklesam.dotalivescore.R
import com.miklesam.steamapi.adapters.LeagueAdapter
import com.miklesam.steamapi.datamodels.League
import com.miklesam.steamapi.ui.tournaments.TournamentsViewModel

class TournamentsFragment : Fragment() {

    private lateinit var tournamentsViewModel: TournamentsViewModel

    companion object{
        var mapa=HashMap<String,String>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tournamentsViewModel =
            ViewModelProviders.of(this).get(TournamentsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tournaments, container, false)
        val recyclerLeague=root.findViewById<RecyclerView>(R.id.recyclerLeague)
        val progressBar=root.findViewById<ProgressBar>(R.id.progress)
        val errorText=root.findViewById<TextView>(R.id.errorText)

        if(savedInstanceState==null){
            tournamentsViewModel.setProgress(true)
            tournamentsViewModel.getTournaments()
        }

        recyclerLeague.layoutManager = LinearLayoutManager(context)
        recyclerLeague.setHasFixedSize(true)
        val adapter = LeagueAdapter()
        recyclerLeague.adapter = adapter

        tournamentsViewModel.getLeagues().observe(this, Observer {
            if(it!=null){
                val teir4=ArrayList<League>()
                teir4.clear()
                for(tour in it) {
                    mapa[tour.league_id] = tour.name
                    if (tour.tier.toInt() > 2 && tour.tier.toInt() < 6) {
                        teir4.add(tour)
                    }
                }
                val teirnew=teir4.asReversed()
                Log.w("HelloMapa",mapa.toString())
                adapter.setLeagues(teirnew)
                tournamentsViewModel.setProgress(false)
            }
        })
        tournamentsViewModel.getErrorLeagues().observe(this, Observer {
            if(it!=null){
                tournamentsViewModel.setProgress(false)
                errorText.text=it
                errorText.visibility= View.VISIBLE
                recyclerLeague.visibility= View.GONE
            }
        })

        tournamentsViewModel.isProgress().observe(this, Observer {
            if(it){
                errorText.visibility= View.GONE
                progressBar.visibility= View.VISIBLE
                recyclerLeague.visibility= View.GONE
            }else{
                progressBar.visibility= View.GONE
                recyclerLeague.visibility= View.VISIBLE
            }
        })

        return root
    }
}
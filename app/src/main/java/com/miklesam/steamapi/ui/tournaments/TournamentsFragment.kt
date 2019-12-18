package com.miklesam.steamapi.ui.tournaments

import android.os.Bundle
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
import com.miklesam.steamapi.R
import com.miklesam.steamapi.adapters.LeagueAdapter

class TournamentsFragment : Fragment() {

    private lateinit var tournamentsViewModel: TournamentsViewModel

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

        recyclerLeague.layoutManager = LinearLayoutManager(context)
        recyclerLeague.setHasFixedSize(true)
        val adapter = LeagueAdapter()
        recyclerLeague.adapter = adapter

        tournamentsViewModel.setProgress(true)

        tournamentsViewModel.getLeagues().observe(this, Observer {
            if(it!=null){
                adapter.setLeagues(it)
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
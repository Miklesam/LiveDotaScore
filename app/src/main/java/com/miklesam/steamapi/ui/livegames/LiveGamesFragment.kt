package com.miklesam.steamapi.ui.livegames

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miklesam.steamapi.R
import com.miklesam.steamapi.adapters.LiveGamesAdapter

class LiveGamesFragment : Fragment() {

    private lateinit var liveGamesViewModel: LiveGamesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        liveGamesViewModel =
            ViewModelProviders.of(this).get(LiveGamesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_livegames, container, false)
        val recyclerLiveGame=root.findViewById<RecyclerView>(R.id.recyclerLiveGames)

        recyclerLiveGame.layoutManager = LinearLayoutManager(context)
        recyclerLiveGame.setHasFixedSize(true)
        val adapter = LiveGamesAdapter()
        recyclerLiveGame.adapter = adapter


        liveGamesViewModel.getLiveGames()


        liveGamesViewModel.returnGames().observe(this, Observer {
            if(it!=null){
                //Log.w("InFragment ", it.size.toString())
                adapter.setGames(it)
            }

        })


        return root
    }
}
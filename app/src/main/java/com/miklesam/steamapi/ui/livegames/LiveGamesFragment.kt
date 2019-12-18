package com.miklesam.steamapi.ui.livegames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.miklesam.steamapi.R

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

        liveGamesViewModel.getLiveGames()


        return root
    }
}
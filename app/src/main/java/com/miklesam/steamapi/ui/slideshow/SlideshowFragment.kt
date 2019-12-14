package com.miklesam.steamapi.ui.slideshow

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
import com.miklesam.steamapi.R
import com.miklesam.steamapi.adapters.LeagueAdapter

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProviders.of(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        val recyclerLeague=root.findViewById<RecyclerView>(R.id.recyclerLeague)
        val progressBar=root.findViewById<ProgressBar>(R.id.progress)
        val errorText=root.findViewById<TextView>(R.id.errorText)

        recyclerLeague.layoutManager = LinearLayoutManager(context)
        recyclerLeague.setHasFixedSize(true)
        val adapter = LeagueAdapter()
        recyclerLeague.adapter = adapter

        slideshowViewModel.setProgress(true)

        slideshowViewModel.getLeagues().observe(this, Observer {
            if(it!=null){
                adapter.setLeagues(it)
                slideshowViewModel.setProgress(false)
            }
        })
        slideshowViewModel.getErrorLeagues().observe(this, Observer {
            if(it!=null){
                slideshowViewModel.setProgress(false)
                errorText.text=it
                errorText.visibility= View.VISIBLE
                recyclerLeague.visibility= View.GONE
            }
        })

        slideshowViewModel.isProgress().observe(this, Observer {
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
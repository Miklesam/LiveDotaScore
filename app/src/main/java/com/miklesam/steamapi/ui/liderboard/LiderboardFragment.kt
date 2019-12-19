package com.miklesam.steamapi.ui.liderboard

import android.os.Bundle
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
import com.miklesam.steamapi.R
import com.miklesam.steamapi.adapters.LidearboardAdapter


class LiderboardFragment : Fragment() {

    private lateinit var liderboardViewModel: LiderboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        liderboardViewModel =
            ViewModelProviders.of(this).get(LiderboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_liderboard, container, false)
        val recyclerLidearboard=root.findViewById<RecyclerView>(R.id.recyclerLidearboard)
        val spinner=root.findViewById<Spinner>(R.id.spinner)
        val linlayout=root.findViewById<LinearLayout>(R.id.linlayout)
        val progressBar=root.findViewById<ProgressBar>(R.id.progress)
        val errorText=root.findViewById<TextView>(R.id.errorText)

        val spinnerAdapter = context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.devision_display,
                R.layout.mylidearboardspinner
            )
        }
        spinnerAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                itemSelected: View?, selectedItemPosition: Int, selectedId: Long
            ) {
                val choose = resources.getStringArray(com.miklesam.steamapi.R.array.devision_request)
                val division=choose[selectedItemPosition]
                liderboardViewModel.setDevision(division)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        recyclerLidearboard.layoutManager = LinearLayoutManager(context)
        recyclerLidearboard.setHasFixedSize(true)
        val adapter = LidearboardAdapter()
        recyclerLidearboard.adapter = adapter

        liderboardViewModel.getPlayers().observe(this, Observer {
            if(it!=null){
                adapter.setPlayers(it)
                liderboardViewModel.setProgress(false)
            }

        })

        liderboardViewModel.getErrorPlayers().observe(this, Observer {
            if(it!=null){
                liderboardViewModel.setProgress(false)
                errorText.text=it
                errorText.visibility= VISIBLE
                linlayout.visibility= GONE
            }
          })


        liderboardViewModel.isProgress().observe(this, Observer {
            if(it){
                errorText.visibility= GONE
                progressBar.visibility=VISIBLE
                linlayout.visibility= GONE
            }else{
                progressBar.visibility=GONE
                linlayout.visibility=VISIBLE
            }
        })

        return root
    }
}
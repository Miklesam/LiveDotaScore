package com.miklesam.livedotascore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        var rankList=ArrayList<String>()
    }

    private val viewModel by lazy {
        ViewModelProviders
            .of(this)
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        get_game.setOnClickListener {
            //viewModel.getLiveGames()
        viewModel.getRanks()
        }





        viewModel.getLoadingState().observe(this, Observer { isLoading ->
            if(isLoading){
                myProgress.visibility= View.VISIBLE
            }else{
                myProgress.visibility= View.INVISIBLE
            }
        })


        viewModel.getContent().observe(this, Observer {
            Log.w("RxallListMapinFr", it)
            var op=ArrayList<String>()
            op.add("01")
            op.add("02")
            var nadapt= RecyclerAdapter()
            val mylm= LinearLayoutManager(this)
            rankdesk.layoutManager=mylm
            rankdesk.adapter=nadapt

        })



    }
}

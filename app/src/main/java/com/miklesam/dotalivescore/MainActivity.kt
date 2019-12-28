package com.miklesam.dotalivescore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log
import com.miklesam.dotalivescore.my_ui.liderboard.LiderboardFragment
import com.miklesam.dotalivescore.my_ui.livegames.LiveGamesFragment
import com.miklesam.dotalivescore.my_ui.tournaments.TournamentsFragment


class MainActivity : AppCompatActivity() {

    var state=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState==null){
            withoutAnim()
            state=1
        }

        first.setOnClickListener {
            showBlack()
            state=1
        }
        second.setOnClickListener {
            showGreen()
            state=2
        }
        third.setOnClickListener {
            showOrange()
            state=3
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("state", state)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = savedInstanceState.getInt("state")
        Log.w("Restore", "state = $state")
    }

    fun showBlack(){
        if(state!=1){
            val transaction=supportFragmentManager.beginTransaction()
            val fragment= LiderboardFragment()
            transaction.setCustomAnimations(R.anim.enter_left_to_right,R.anim.exit_left_to_right,R.anim.enter_right_to_left,R.anim.exit_right_to_left)
            transaction.replace(R.id.fragment_holder,fragment)
            transaction.commit()
        }

    }

    fun withoutAnim(){
        val transaction=supportFragmentManager.beginTransaction()
        val fragment= LiderboardFragment()
        transaction.replace(R.id.fragment_holder,fragment)
        transaction.commit()
    }

    fun showGreen(){
        if (state!=2){
            val transaction=supportFragmentManager.beginTransaction()
            val fragment= LiveGamesFragment()
            if(state==1){
                transaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,R.anim.enter_left_to_right,R.anim.exit_left_to_right)
            }else{
                transaction.setCustomAnimations(R.anim.enter_left_to_right,R.anim.exit_left_to_right,R.anim.enter_right_to_left,R.anim.exit_right_to_left)
            }
            transaction.replace(R.id.fragment_holder,fragment)
            transaction.commit()
        }

    }

    fun showOrange(){
        if (state!=3){
            val transaction=supportFragmentManager.beginTransaction()
            val fragment= TournamentsFragment()
            transaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,R.anim.enter_left_to_right,R.anim.exit_left_to_right)
            transaction.replace(R.id.fragment_holder,fragment)
            transaction.commit()
        }
    }



}

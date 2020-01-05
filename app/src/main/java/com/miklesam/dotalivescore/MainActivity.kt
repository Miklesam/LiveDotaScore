package com.miklesam.dotalivescore

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.miklesam.dotalivescore.my_ui.liderboard.LeaderboardFragment
import com.miklesam.dotalivescore.my_ui.livegames.LiveGamesFragment
import com.miklesam.dotalivescore.my_ui.tournaments.TournamentsFragment


class MainActivity : AppCompatActivity() {

    var state=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firstIma=findViewById<ImageView>(R.id.image_first)
        val secondIma=findViewById<ImageView>(R.id.image_second)
        val thirdIma=findViewById<ImageView>(R.id.image_third)
        if(savedInstanceState==null){
            withoutAnim()
            state=1
            checkState()
        }

        first.setOnClickListener {
            showBlack()
            state=1
            checkState()
        }
        second.setOnClickListener {
            showGreen()
            state=2
            checkState()
        }
        third.setOnClickListener {
            showOrange()
            state=3
            checkState()
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
        checkState()
    }

    fun checkState(){
        if(state==1){
            DrawableCompat.setTint(image_first.getDrawable(), ContextCompat.getColor(this, R.color.colorPrimaryDark));
            DrawableCompat.setTint(image_second.getDrawable(), ContextCompat.getColor(this, R.color.colorAccent));
            DrawableCompat.setTint(image_third.getDrawable(), ContextCompat.getColor(this, R.color.colorAccent));
            text_first.setTextColor(Color.parseColor("#1b1b1b"))
            text_second.setTextColor(Color.parseColor("#6d6d6d"))
            text_third.setTextColor(Color.parseColor("#6d6d6d"))
        }else if(state==2){
            DrawableCompat.setTint(image_first.getDrawable(), ContextCompat.getColor(this, R.color.colorAccent));
            DrawableCompat.setTint(image_second.getDrawable(), ContextCompat.getColor(this, R.color.colorPrimaryDark));
            DrawableCompat.setTint(image_third.getDrawable(), ContextCompat.getColor(this, R.color.colorAccent));
            text_first.setTextColor(Color.parseColor("#6d6d6d"))
            text_second.setTextColor(Color.parseColor("#1b1b1b"))
            text_third.setTextColor(Color.parseColor("#6d6d6d"))
        }else{
            DrawableCompat.setTint(image_first.getDrawable(), ContextCompat.getColor(this, R.color.colorAccent));
            DrawableCompat.setTint(image_second.getDrawable(), ContextCompat.getColor(this, R.color.colorAccent));
            DrawableCompat.setTint(image_third.getDrawable(), ContextCompat.getColor(this, R.color.colorPrimaryDark));
            text_first.setTextColor(Color.parseColor("#6d6d6d"))
            text_second.setTextColor(Color.parseColor("#6d6d6d"))
            text_third.setTextColor(Color.parseColor("#1b1b1b"))
        }
    }

    fun showBlack(){
        if(state!=1){
            val transaction=supportFragmentManager.beginTransaction()
            //val fragment= LeaderboardFragment()
            val fragment= TournamentsFragment()
            transaction.setCustomAnimations(R.anim.enter_left_to_right,R.anim.exit_left_to_right,R.anim.enter_right_to_left,R.anim.exit_right_to_left)
            transaction.replace(R.id.fragment_holder,fragment)
            transaction.commit()
        }

    }

    fun withoutAnim(){
        val transaction=supportFragmentManager.beginTransaction()
        //val fragment= LeaderboardFragment()
        val fragment= TournamentsFragment()
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
            //val fragment= TournamentsFragment()
            val fragment= LeaderboardFragment()
            transaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,R.anim.enter_left_to_right,R.anim.exit_left_to_right)
            transaction.replace(R.id.fragment_holder,fragment)
            transaction.commit()
        }
    }



}

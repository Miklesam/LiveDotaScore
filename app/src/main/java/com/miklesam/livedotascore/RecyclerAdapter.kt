package com.miklesam.livedotascore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val cont=parent.context
        val inf= LayoutInflater.from(cont)
        val layoutIdForList= R.layout.example_item
        val view=inf.inflate(layoutIdForList,parent,false)
        return NumberViewHolder(view)
    }

    override fun getItemCount(): Int {
        return MainActivity.rankList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is NumberViewHolder->{
                holder.Title.text=MainActivity.rankList.get(position)
            }
        }
    }



    class NumberViewHolder : RecyclerView.ViewHolder {
        val Title = itemView.findViewById<TextView>(R.id.tetView)
        constructor(itemView: View) : super(itemView) {}

    }






}
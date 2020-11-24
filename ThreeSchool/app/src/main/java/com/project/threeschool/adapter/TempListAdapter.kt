package com.project.threeschool.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.threeschool.R
import com.project.threeschool.model.Members

class TempListAdapter(val list : ArrayList<Members>):RecyclerView.Adapter<TempListAdapter.Holder>() {
    var n : Int = 1
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val numText = itemView.findViewById<TextView>(R.id.numText)
        val tempText = itemView.findViewById<TextView>(R.id.tempText)
        val checkText = itemView.findViewById<TextView>(R.id.checkText)

        fun bind(m : Members) {
            numText.text = n.toString()
            n++
            tempText.text = m.temperature.toString()

            if(m.checkTemp){
                checkText.text = "정상"
            }else{
                checkText.text = "비정상"
            }

            itemView.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
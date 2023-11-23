package com.example.whatapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppAdapter(
    private val dataList: List<DataClass>,
    private val onItemClickListener: addapplicationtab
) : RecyclerView.Adapter<AppAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        var currentItem = dataList[p1]

        p0.rvImage.setImageResource(currentItem.dataImage)
        p0.rvTitle.text = currentItem.dataTitle
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class MyViewHolder(itemView: View, private val onItemClickListener: addapplicationtab) :
        RecyclerView.ViewHolder(itemView) {

        var rvImage: ImageView = itemView.findViewById(R.id.recyclerImage)
        var rvTitle: TextView = itemView.findViewById(R.id.recyclerTitle)

        init {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(adapterPosition)
            }
        }
    }
}
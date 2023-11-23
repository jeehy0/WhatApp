package com.example.whatapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppAdapter(val dataList : ArrayList<DataClass>, private val onItemClick: (DataClass) -> Unit) : RecyclerView.Adapter<AppAdapter.ViewHolderClass>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.item_layout, p0, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(p0: ViewHolderClass, p1: Int) {
        var currentItem = dataList[p1]
        p0.rvImage.setImageResource(currentItem.dataImage)
        p0.rvTitle.text = currentItem.dataTitle
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rvImage: ImageView = itemView.findViewById(R.id.recyclerImage)
        var rvTitle: TextView = itemView.findViewById(R.id.recyclerTitle)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(dataList[position])
                }
            }
        }
    }
}
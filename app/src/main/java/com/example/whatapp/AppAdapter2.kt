package com.example.whatapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppAdapter2(
    private val titleList: List<String>,
    private val drawableList: List<Int>,
    private val clickListener: (Int) -> Unit // Click listener for item clicks
) : RecyclerView.Adapter<AppAdapter2.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(titleList[position], drawableList[position])
        holder.itemView.setOnClickListener {
            clickListener(position) // Invoke the click listener passing the position
        }
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.recyclerTitle)
        private val iconImageView: ImageView = itemView.findViewById(R.id.recyclerImage)

        fun bind(title: String, drawableResId: Int) {
            titleTextView.text = title
            iconImageView.setImageResource(drawableResId)
        }
    }
}
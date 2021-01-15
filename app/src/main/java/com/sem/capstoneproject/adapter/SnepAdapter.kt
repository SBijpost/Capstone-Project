package com.sem.capstoneproject.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sem.capstoneproject.R
import com.sem.capstoneproject.model.SnepItem
import kotlinx.android.synthetic.main.item_snep.view.*

class SnepAdapter(private val sneps: List<SnepItem>, private val onClick: (SnepItem) -> Unit) :
    RecyclerView.Adapter<SnepAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_snep, parent, false)
        )
    }

    override fun getItemCount(): Int = sneps.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(sneps[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onClick(sneps[adapterPosition]) }
        }

        fun bind(snepItem: SnepItem) {
//            Glide.with(context).load(snepItem.getImageUrl()).into(itemView.movie_photo)
//            itemView.movie_number.text = (adapterPosition + 1).toString()
            itemView.nameTV.text = snepItem.senderName
        }
    }

}
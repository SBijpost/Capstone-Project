package com.sem.capstoneproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sem.capstoneproject.R
import com.sem.capstoneproject.model.Friend
import kotlinx.android.synthetic.main.item_snep.view.*

class FriendsAdapter(private val friends: List<Friend>, private val onClick: (Friend) -> Unit) :
    RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_friend, parent, false)
        )
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(friends[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onClick(friends[adapterPosition]) }
        }

        fun bind(friend: Friend) {
            itemView.nameTV.text = friend.name
        }
    }

}
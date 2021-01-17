package com.sem.capstoneproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sem.capstoneproject.R
import com.sem.capstoneproject.model.Friend

class SelectReceiverAdapter(private val friends: List<Friend>, var c: Context) :
    RecyclerView.Adapter<SelectReceiverAdapter.MyHolder>() {

    var checkedFriends = ArrayList<Friend>()

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.friends_checkbox_row, null)
        return MyHolder(v)
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val friend = friends[position]
        holder.nameTxt.text = friend.name
        holder.myCheckBox.isChecked = friend.selected

        holder.setItemClickListener(object : MyHolder.ItemClickListener {
            override fun onItemClick(v: View, pos: Int) {
                val myCheckBox = v as CheckBox
                val currentFriend = friends[pos]

                if (myCheckBox.isChecked) {
                    currentFriend.selected = true
                    checkedFriends.add(currentFriend)
                } else if (!myCheckBox.isChecked) {
                    currentFriend.selected = false
                    checkedFriends.remove(currentFriend)
                }
            }
        })
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var nameTxt: TextView
        var myCheckBox: CheckBox

        lateinit var myItemClickListener: ItemClickListener

        init {
            nameTxt = itemView.findViewById(R.id.nameTV)
            myCheckBox = itemView.findViewById(R.id.selectCB)

            myCheckBox.setOnClickListener(this)
        }

        fun setItemClickListener(ic: ItemClickListener) {
            this.myItemClickListener = ic
        }

        interface ItemClickListener {
            fun onItemClick(v: View, pos: Int)
        }

        override fun onClick(v: View) {
            this.myItemClickListener.onItemClick(v, layoutPosition)
        }
    }

}
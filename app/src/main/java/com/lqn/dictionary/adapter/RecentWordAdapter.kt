package com.lqn.dictionary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lqn.dictionary.R
import com.lqn.dictionary.callback.recentword.OnItemListener
import kotlinx.android.synthetic.main.layout_item_recent_word.view.*

class RecentWordAdapter(var context: Context, var mWords: ArrayList<String>, var onItemListener: OnItemListener) :
    RecyclerView.Adapter<RecentWordAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_recent_word, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemView = holder.itemView
        val word = mWords[position]

        itemView.tvWord.text = word

        itemView.setOnClickListener {
            // handle gift click
            onItemListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return mWords.size
    }
}
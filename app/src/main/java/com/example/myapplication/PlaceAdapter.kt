package com.example.myapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.myapplication.Model.Place

open class PlaceAdapter(private val context: Context, private val places: List<Place>, private val listener: Listener?) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>()
{
    //行番号取得用インターフェイス
    interface Listener
    {
        fun onItemClicked(position: Int) {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) : PlaceViewHolder
    {
        val view = LayoutInflater.from(context).inflate(R.layout.item_place, parent, false)

        val holder = PlaceViewHolder(view)
        holder.itemView.setOnClickListener {
            //ViewHolderのpositionで行番号を取得
            listener?.onItemClicked(holder.adapterPosition)
        }

        return holder
    }

    override fun getItemCount() = places.size

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int)
    {
        holder.placeNameTextView.text = places[position].name
        holder.cityNameTextView.text = places[position].city
    }

    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val placeNameTextView: TextView = itemView.findViewById(R.id.placeNameTextView)
        val cityNameTextView: TextView = itemView.findViewById(R.id.cityNameTextView)
    }
}
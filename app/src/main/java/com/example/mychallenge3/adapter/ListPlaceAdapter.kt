package com.example.mychallenge3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mychallenge3.R
import com.example.mychallenge3.data.model.Place

class ListPlaceAdapter (private val listPlace: ArrayList<Place>) : RecyclerView.Adapter<ListPlaceAdapter.ListViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName : TextView =itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView =itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListPlaceAdapter.ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListPlaceAdapter.ListViewHolder, position: Int) {
        val(name,description,photo)=listPlace[position]
        Glide.with(holder.itemView.context)
            .load(photo) // URL Gambar
            .into(holder.imgPhoto)
        holder.tvName.text=name
        holder.tvDescription.text=description
        holder.itemView.setOnClickListener(){
            Toast.makeText(holder.itemView.context,"Kamu memilih "+listPlace[holder.adapterPosition].name,Toast.LENGTH_SHORT).show()
            onItemClickCallback.onItemClicked(listPlace[holder.adapterPosition])

        }
    }

    override fun getItemCount(): Int {
        return listPlace.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Place)
    }

}
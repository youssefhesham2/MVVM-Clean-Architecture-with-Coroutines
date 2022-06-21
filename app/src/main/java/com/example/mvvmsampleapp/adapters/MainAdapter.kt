package com.example.mvvmsampleapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entities.UserDomainEntity
import com.example.mvvmsampleapp.R


class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private var users: ArrayList<UserDomainEntity> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = users.get(position)
        holder.textViewUserName.text = user.name
        holder.textViewUserEmail.text = user.email
        Glide.with(holder.imageViewAvatar.context)
            .load(user.avatar)
            .into(holder.imageViewAvatar)
    }

    override fun getItemCount(): Int = users.size

    fun addData(list: List<UserDomainEntity>) {
        users.addAll(list)
        notifyDataSetChanged()
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewAvatar: ImageView = itemView.findViewById(R.id.imageViewAvatar)
        val textViewUserName: TextView = itemView.findViewById(R.id.textViewUserName)
        val textViewUserEmail: TextView = itemView.findViewById(R.id.textViewUserEmail)

    }
}
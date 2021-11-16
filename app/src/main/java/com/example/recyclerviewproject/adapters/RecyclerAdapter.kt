package com.example.recyclerviewproject.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewproject.databinding.AdapterListBinding
import com.example.recyclerviewproject.response.Data

class RecyclerAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var data = mutableListOf<Data>()

    @SuppressLint("NotifyDataSetChanged")
    @JvmName("setData1")
    fun setData(incomingData : List<Data>) {
        this.data = incomingData.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterListBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = data[position]
        holder.binding.name.text = user.name
        holder.binding.email.text = user.email
        holder.binding.gender.text = user.gender
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
class MainViewHolder(val binding: AdapterListBinding) : RecyclerView.ViewHolder(binding.root){}
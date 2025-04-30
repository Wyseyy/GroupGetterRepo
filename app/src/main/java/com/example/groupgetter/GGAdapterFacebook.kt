package com.example.groupgetter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GGAdapterFacebook(private val pages: List<FacebookPages>) : RecyclerView.Adapter<GGAdapterFacebook.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nameOfPage: TextView = view.findViewById(R.id.facebookPage)
        val categoryOfPage: TextView = view.findViewById(R.id.facebookCategory)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.page_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val page = pages[position]
        holder.nameOfPage.text = page.name
        holder.categoryOfPage.text = page.category
    }

    override fun getItemCount(): Int = pages.size

}
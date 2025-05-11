package com.example.groupgetter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//This is an adapter class for displaying messages in a RecyclerView
class AdapterMessage(private val messages: List<Message>) :
    RecyclerView.Adapter<AdapterMessage.MessageViewHolder>() {

        //ViewHolder for viewing elements of each item in the RecyclerView
        inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val messageText: TextView = itemView.findViewById(R.id.messageText)
            val usernameText: TextView = itemView.findViewById(R.id.messageUser)
        }

        //Method to be called when a viewHolder is needed
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.message_text, parent, false)
            return MessageViewHolder(view)
        }

        //Method to bind a message to the viewHolder
        override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
            val message = messages[position]
            holder.messageText.text = message.message
            holder.usernameText.text = message.username
        }

        //Returms the items in the list
        override fun getItemCount(): Int = messages.size
}
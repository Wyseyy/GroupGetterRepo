package com.example.groupgetter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterMessage(private val messages: List<Message>) :
    RecyclerView.Adapter<AdapterMessage.MessageViewHolder>() {

        inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val messageText: TextView = itemView.findViewById(R.id.messageText)
            val usernameText: TextView = itemView.findViewById(R.id.messageUser)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.message_text, parent, false)
            return MessageViewHolder(view)
        }

        override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
            val message = messages[position]
            holder.messageText.text = message.message
            holder.usernameText.text = message.username
        }

        override fun getItemCount(): Int = messages.size
}
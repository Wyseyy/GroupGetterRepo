package com.example.groupgetter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Adapter class for displaying user messages with their username in a RecyclerView
public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.MessageViewHolder> {

    private ArrayList<Message> messages;

    public CommunityAdapter(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_text, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Message currentMessage = messages.get(position);
        holder.messageText.setText(currentMessage.getMessage());
        holder.messageUser.setText(currentMessage.getUsername());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView messageUser;

        public MessageViewHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
            messageUser = itemView.findViewById(R.id.messageUser);
        }
    }
}

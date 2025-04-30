package com.example.groupgetter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GGAdapter(private var subredditList: List<SubredditInformation>) :
        RecyclerView.Adapter<GGAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subName: TextView = view.findViewById(R.id.subName)
        val subTitle: TextView = view.findViewById(R.id.subTitle)
        val subDescription: TextView = view.findViewById(R.id.subDescription)
        val subIcon: ImageView = view.findViewById(R.id.subIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.subreddit_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subreddit = subredditList[position]
        holder.subName.text = subreddit.display_name
        holder.subTitle.text = subreddit.title
        holder.subDescription.text = subreddit.description

        Glide.with(holder.itemView.context)
                .load(subreddit.icon)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error_icon)
                .into(holder.subIcon)
    }

    fun update(newSubreddits: List<SubredditInformation>) {
        subredditList = newSubreddits
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = subredditList.size
}

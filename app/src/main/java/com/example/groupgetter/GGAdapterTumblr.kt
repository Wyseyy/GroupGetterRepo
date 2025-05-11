package com.example.groupgetter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
//Adapter for displaying Tumblr and its Blogs in name, description and avatar
data class TumblrBlog(
        val name: String,
        val title: String?,
        val description: String?,
        val avatar: String? = null
)
class GGAdapterTumblr (
    private val blogs: List<TumblrBlog>,
    private val onItemClick: (TumblrBlog) -> Unit
): RecyclerView.Adapter<GGAdapterTumblr.TumblrViewHolder>(){

    class TumblrViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val blogName: TextView = view.findViewById(R.id.blog_name)
        val blogDescription: TextView = view.findViewById(R.id.blog_description)
        val blogAvatar: ImageView = view.findViewById(R.id.avatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TumblrViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.tumblr_blog_item, parent, false)
        return TumblrViewHolder(view)
    }

    override fun onBindViewHolder(holder: TumblrViewHolder, position: Int) {
        val blog = blogs[position]
        holder.blogName.text = blog.name ?: blog.name
        holder.blogDescription.text = blog.description ?: "No description found"

        if (blog.avatar != null) {
            Glide.with(holder.itemView.context)
                    .load(blog.avatar)
                    .into(holder.blogAvatar)
        } else {
            holder.blogAvatar.setImageResource(R.drawable.default_avatar)
        }
        holder.itemView.setOnClickListener {
            onItemClick(blog)
        }
    }

    override fun getItemCount(): Int = blogs.size
}
package com.daniel.pruebadeingreso.ui.posts

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daniel.domain.Post
import com.daniel.pruebadeingreso.R
import com.daniel.pruebadeingreso.databinding.ViewItemPostBinding
import com.daniel.pruebadeingreso.ui.common.inflate
import com.daniel.pruebadeingreso.ui.common.mutableBasicDiffUtil

class PostAdapter() : RecyclerView.Adapter<PostAdapter.ViewHolder>(){

    var posts: MutableList<Post> by mutableBasicDiffUtil(
        mutableListOf(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = ViewItemPostBinding.bind(view)
        fun bind(post: Post) = with(binding){
            binding.txtItemTitle.text = post.title
            binding.txtItemBody.text = post.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_item_post, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = posts[position]
        holder.bind(p)
    }

    override fun getItemCount(): Int = posts.size

}
package com.bykov.igor.githubstars.presentation.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bykov.igor.githubstars.R
import com.bykov.igor.githubstars.data.user.model.GithubUser

class UserAdapter(private val users: List<GithubUser>) : RecyclerView.Adapter<UserViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
    return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_view, parent, false))
  }

  override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
    val user = users[position]
    holder.userName.text = user.login
    Glide.with(holder.userImage.context).load(user.avatarUrl).into(holder.userImage)
  }

  override fun getItemCount() = users.size
}
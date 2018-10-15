package com.bykov.igor.githubstars.presentation.ui.users.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bykov.igor.githubstars.R

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

  val view: View = view.findViewById(R.id.userView)
  val userName: TextView = view.findViewById(R.id.userName)
  val userImage: ImageView = view.findViewById(R.id.userImage)
  val navigateToUser: View = view.findViewById(R.id.navigateToUser)
}
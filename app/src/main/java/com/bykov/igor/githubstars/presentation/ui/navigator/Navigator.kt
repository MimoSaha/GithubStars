package com.bykov.igor.githubstars.presentation.ui.navigator

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import com.bykov.igor.githubstars.presentation.ui.details.USER_GITHUB_URL
import com.bykov.igor.githubstars.presentation.ui.details.USER_IMAGE_URL
import com.bykov.igor.githubstars.presentation.ui.details.USER_NAME
import com.bykov.igor.githubstars.presentation.ui.details.UserDetailActivity

class Navigator(val context: Context) {

  fun navigateToDetails(userName: String, userImageUrl: String, userGithubUrl: String) {
    val intent = Intent(context, UserDetailActivity::class.java).apply {
      putExtra(USER_NAME, userName)
      putExtra(USER_IMAGE_URL, userImageUrl)
      putExtra(USER_GITHUB_URL, userGithubUrl)
    }
    context.startActivity(intent)
  }

  fun navigateToUrl(url: String) {
    context.startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
  }

}
package com.bykov.igor.githubstars.presentation.ui.details

import android.os.Bundle
import androidx.lifecycle.LifecycleObserver
import com.bumptech.glide.Glide
import com.bykov.igor.githubstars.R
import com.bykov.igor.githubstars.presentation.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_user_details.*

const val USER_NAME = "user_name_extra "
const val USER_IMAGE_URL = "user_image_url_extra "
const val USER_GITHUB_URL = "user_github_url_extra "
class UserDetailActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_user_details)
    intent?.apply {
      userName.text = getStringExtra(USER_NAME)
      Glide.with(userImage).load(getStringExtra(USER_IMAGE_URL)).into(userImage)
      userUrl.text = getStringExtra(USER_GITHUB_URL)
    }

    userUrlNavigation.setOnClickListener { navigator.navigateToUrl(userUrl.text.toString()) }
  }

  override fun presenter(): LifecycleObserver? = null
}

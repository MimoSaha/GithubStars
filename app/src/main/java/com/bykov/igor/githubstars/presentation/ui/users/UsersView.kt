package com.bykov.igor.githubstars.presentation.ui.users

import com.bykov.igor.githubstars.data.user.model.GithubUser
import com.bykov.igor.githubstars.presentation.mvp.MvpView

interface UsersView : MvpView {

  fun renderUsers(users: List<GithubUser>)
}
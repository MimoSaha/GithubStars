package com.bykov.igor.githubstars.presentation.ui.users

import com.bykov.igor.githubstars.domain.user.GetUsers
import com.bykov.igor.githubstars.presentation.mvp.BasePresenter
import kotlinx.coroutines.experimental.launch

class UsersPresenter(private val getUsers: GetUsers) : BasePresenter<UsersView>() {


  fun loadUsers() {
    this.launch {

      val result = getUsers.buildUseCaseObservable(Unit).await()

      view.renderUsers(result)
    }
  }
}
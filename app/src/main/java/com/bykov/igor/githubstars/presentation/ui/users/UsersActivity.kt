package com.bykov.igor.githubstars.presentation.ui.users

import android.os.Bundle
import android.service.autofill.UserData
import androidx.recyclerview.widget.LinearLayoutManager
import com.bykov.igor.githubstars.R
import com.bykov.igor.githubstars.data.user.model.GithubUser
import com.bykov.igor.githubstars.presentation.ui.BaseActivity
import com.bykov.igor.githubstars.presentation.ui.users.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import timber.log.Timber

class UsersActivity : BaseActivity(), KodeinAware, UsersView {

  override val kodein: Kodein by closestKodein()

  private val presenter: UsersPresenter by instance()
  private var users = emptyList<GithubUser>()
  private val adapter : UserAdapter by lazy { UserAdapter(users) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    presenter.bind(this)
    presenter.loadUsers()
  }

  override fun presenter() = presenter

  override fun renderUsers(users: List<GithubUser>) {
    this.users = users
    usersRV.layoutManager = LinearLayoutManager(this)
    usersRV.adapter = adapter
  }
}

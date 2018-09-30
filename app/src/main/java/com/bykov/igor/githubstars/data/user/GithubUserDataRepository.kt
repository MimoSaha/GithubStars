package com.bykov.igor.githubstars.data.user

import com.bykov.igor.githubstars.data.network.GithubService
import com.bykov.igor.githubstars.data.user.model.GithubUser
import com.bykov.igor.githubstars.domain.user.repository.GithubUserRepository
import kotlinx.coroutines.experimental.Deferred
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class GithubUserDataRepository(override val kodein: Kodein) : GithubUserRepository, KodeinAware {

  private val service: GithubService by instance()

  override fun getStarUsers(): Deferred<List<GithubUser>> {
    return service.getUser()
  }
}
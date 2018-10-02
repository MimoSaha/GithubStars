package com.bykov.igor.githubstars.data.user

import com.bykov.igor.githubstars.data.network.GithubService
import com.bykov.igor.githubstars.data.user.model.GithubRepsonse
import com.bykov.igor.githubstars.data.user.model.GithubUser
import com.bykov.igor.githubstars.domain.user.repository.GithubUserRepository
import kotlinx.coroutines.experimental.Deferred

class GithubUserDataRepository(private val service: GithubService) : GithubUserRepository {

  override fun getStarUsers(): Deferred<GithubRepsonse> {
    return service.getUser()
  }
}
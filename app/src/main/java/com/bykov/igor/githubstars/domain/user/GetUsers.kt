package com.bykov.igor.githubstars.domain.user

import com.bykov.igor.githubstars.data.user.model.GithubUser
import com.bykov.igor.githubstars.domain.UseCase
import com.bykov.igor.githubstars.domain.user.repository.GithubUserRepository
import kotlinx.coroutines.experimental.Deferred

class GetUsers(private val repository : GithubUserRepository) : UseCase<List<GithubUser>, Unit>() {

  override fun buildUseCaseObservable(params: Unit): Deferred<List<GithubUser>> {
    return repository.getStarUsers()
  }
}
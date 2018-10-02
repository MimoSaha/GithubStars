package com.bykov.igor.githubstars.domain.user.interactor

import com.bykov.igor.githubstars.data.user.model.GithubRepsonse
import com.bykov.igor.githubstars.domain.UseCase
import com.bykov.igor.githubstars.domain.user.repository.GithubUserRepository
import kotlinx.coroutines.experimental.Deferred

class GetUsers(private val repository : GithubUserRepository) : UseCase<GithubRepsonse, Unit>() {

  override fun buildUseCaseObservable(params: Unit): Deferred<GithubRepsonse> {
    return repository.getStarUsers()
  }
}
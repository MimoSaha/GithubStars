package com.bykov.igor.githubstars.domain.user

import com.bykov.igor.githubstars.data.user.model.GithubUser
import com.bykov.igor.githubstars.domain.UseCase
import com.bykov.igor.githubstars.domain.user.repository.GithubUserRepository
import kotlinx.coroutines.experimental.Deferred
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class GetUsers(override val kodein: Kodein) : UseCase<List<GithubUser>, Unit>() {

  private val resopository : GithubUserRepository by instance()

  override fun buildUseCaseObservable(params: Unit): Deferred<List<GithubUser>> {
    return resopository.getStarUsers()
  }
}
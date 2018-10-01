package com.bykov.igor.githubstars.domain.user.repository

import com.bykov.igor.githubstars.data.user.model.GithubUser
import kotlinx.coroutines.experimental.Deferred

interface GithubUserRepository {

  fun getStarUsers() : Deferred<List<GithubUser>>
}
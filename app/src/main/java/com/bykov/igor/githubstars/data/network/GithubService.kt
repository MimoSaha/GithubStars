package com.bykov.igor.githubstars.data.network

import com.bykov.igor.githubstars.data.user.model.GithubUser
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface GithubService {

  @GET("/user")
  fun getUser(): Deferred<List<GithubUser>>
}
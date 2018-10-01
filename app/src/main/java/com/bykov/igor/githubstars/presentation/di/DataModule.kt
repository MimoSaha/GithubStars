package com.bykov.igor.githubstars.presentation.di

import com.bykov.igor.githubstars.data.user.GithubUserDataRepository
import com.bykov.igor.githubstars.domain.user.GetUsers
import com.bykov.igor.githubstars.domain.user.repository.GithubUserRepository
import org.kodein.di.Kodein
import org.kodein.di.android.AndroidComponentsWeakScope
import org.kodein.di.generic.*

object DataModule {

  val module = Kodein.Module("DataModule") {

    bind<GithubUserRepository>() with scoped(AndroidComponentsWeakScope).singleton { GithubUserDataRepository(instance()) }

    bind<GetUsers>() with provider { GetUsers(instance()) }
  }
}
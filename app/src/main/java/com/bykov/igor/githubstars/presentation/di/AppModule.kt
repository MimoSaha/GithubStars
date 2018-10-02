package com.bykov.igor.githubstars.presentation.di

import com.bykov.igor.githubstars.domain.images.LoadImage
import com.bykov.igor.githubstars.presentation.slices.SliceCategoryPresenter
import com.bykov.igor.githubstars.presentation.ui.users.UsersPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

object AppModule {

  val module = Kodein.Module("AppModule") {

    bind<UsersPresenter>() with provider { UsersPresenter(instance()) }

    bind<SliceCategoryPresenter>() with provider { SliceCategoryPresenter(instance(), LoadImage()) }

  }
}
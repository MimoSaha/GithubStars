package com.bykov.igor.githubstars.presentation.slices

import android.net.Uri
import com.bykov.igor.githubstars.domain.user.GetUsers
import com.bykov.igor.githubstars.presentation.mvp.BasePresenter
import kotlinx.coroutines.experimental.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class SliceCategoryPresenter(override val kodein: Kodein) : BasePresenter<SliceView>(), KodeinAware {

  private val getUsers: GetUsers by instance()

  fun getProperties(sliceUri: Uri, stringInput: String) {
    this.launch {

      val results = getUsers.buildUseCaseObservable(Unit).await()

      view.rednerUsers(sliceUri, results)
    }
  }
}
package com.bykov.igor.githubstars.presentation.slices

import android.graphics.Bitmap
import android.net.Uri
import com.bykov.igor.githubstars.data.user.model.GithubUser
import com.bykov.igor.githubstars.domain.images.LoadImage
import com.bykov.igor.githubstars.domain.user.interactor.GetUsers
import com.bykov.igor.githubstars.presentation.mvp.BasePresenter
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch


class SliceCategoryPresenter(
    private val getUsers: GetUsers,
    private val loadImage: LoadImage
) : BasePresenter<SliceView>() {

  fun getUsers(sliceUri: Uri) {
    this.launch {
      val results = getUsers.buildUseCaseObservable(Unit).await()
      view.renderUsers(sliceUri, results.items)
    }
  }

  fun loadImages(sliceUri: Uri, users : List<GithubUser>) {

      val results = mutableListOf<Pair<String, Bitmap>>()
      for (user in users) {
        this.launch {
        val response = async { loadImage.buildUseCaseObservable(user.avatarUrl) }
        val bitmap = response.await()
        results.add(Pair(user.avatarUrl, bitmap))
      }
      view.updateBitmaps(sliceUri, results)
    }
  }
}
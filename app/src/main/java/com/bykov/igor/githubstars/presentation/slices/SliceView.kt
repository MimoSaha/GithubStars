package com.bykov.igor.githubstars.presentation.slices

import android.graphics.Bitmap
import android.net.Uri
import com.bykov.igor.githubstars.data.user.model.GithubUser
import com.bykov.igor.githubstars.presentation.mvp.MvpView

interface SliceView : MvpView {

    fun renderUsers(sliceUri: Uri, users: List<GithubUser>)

    fun updateBitmaps(sliceUri: Uri, bitmaps: List<Pair<String, Bitmap>>)
}
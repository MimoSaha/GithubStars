package com.bykov.igor.githubstars.presentation.slices

import android.net.Uri
import com.bykov.igor.githubstars.data.user.model.GithubUser
import com.bykov.igor.githubstars.presentation.mvp.MvpView

interface SliceView : MvpView {

    fun rednerUsers(sliceUri: Uri, users: List<GithubUser>)
}
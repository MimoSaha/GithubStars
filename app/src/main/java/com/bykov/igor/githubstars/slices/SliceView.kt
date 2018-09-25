package com.bykov.igor.githubstars.slices

import android.net.Uri
import com.example.ibykov.searchresult.model.SearchResultResponse
import com.example.ibykov.searchresult.ui.MvpView

interface SliceView : MvpView {

    fun onSliveView(sliceUri: Uri, searchResultUseCase: SearchResultResponse)
}
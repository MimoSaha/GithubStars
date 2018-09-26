package com.bykov.igor.githubstars.slices

import android.net.Uri
import com.example.ibykov.searchresult.domain.AutoCompleteUseCase
import com.example.ibykov.searchresult.domain.SearchResultUseCase
import com.example.ibykov.searchresult.model.Category
import com.example.ibykov.searchresult.ui.BasePresenter
import com.example.ibykov.searchresult.ui.autocomplete.mapper.SuggestionMapper
import com.example.ibykov.searchresult.ui.autocomplete.mapper.SuggestionModel
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SliceCategoryPresenter @Inject constructor(val search: SearchResultUseCase, val autoCompleteUseCase: AutoCompleteUseCase) : BasePresenter<SliceView>() {

    fun getProperties(sliceUri: Uri, stringInput: String, autoCompleteSuggestion: SuggestionModel?, category: Category?) {
        val d = autoCompleteUseCase.buildUseCase(stringInput).flatMap {
            return@flatMap search.buildUseCase(SearchResultUseCase.Param(SuggestionMapper.mapSuggestion(it[0]), category))

        }.subscribeOn(Schedulers.io())
                .subscribe(Consumer { view.onSliveView(sliceUri, it) })
    }
}
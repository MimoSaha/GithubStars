package com.bykov.igor.githubstars.slices

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction
import androidx.slice.builders.list
import androidx.slice.builders.row
import com.bykov.igor.githubstars.R
import com.bykov.igor.githubstars.presentation.MainActivity

class GithubStarsProvider : SliceProvider() {

  override fun onBindSlice(sliceUri: Uri): Slice {
    val activityAction = createActivityAction()
    return if (sliceUri.path == "/hello") {
      list(context, sliceUri, ListBuilder.INFINITY) {
        row {
          primaryAction = activityAction
          title = "Hello."
        }
      }
    } else {
      list(context, sliceUri, ListBuilder.INFINITY) {
        row {
          primaryAction = activityAction
          title = "URI not recognized."
        }
      }
    }
  }

  override fun onCreateSliceProvider(): Boolean {
    return true
  }

  fun createActivityAction(): SliceAction {
    val intent = Intent(context, MainActivity::class.java)
    return SliceAction.create(
        PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), 0),
        IconCompat.createWithResource(context, R.drawable.ic_star_black_24dp),
        ListBuilder.LARGE_IMAGE,
        "Enter app"
    )
  }
}
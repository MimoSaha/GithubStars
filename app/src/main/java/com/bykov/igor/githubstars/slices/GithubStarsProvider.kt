package com.bykov.igor.githubstars.slices

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.text.TextUtils
import android.util.LruCache
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.*
import com.bumptech.glide.Glide
import com.bykov.igor.githubstars.R
import com.bykov.igor.githubstars.presentation.MainActivity
import java.time.LocalDate

class GithubStarsProvider : SliceProvider() {

  private val cache = LruCache<String, Bitmap?>(15)
  private val cacheResponse = LruCache<Uri, SearchResultResponse?>(15)

  override fun onCreateSliceProvider(): Boolean {
    presenter.bind(this)
    return true
  }

  override fun onMapIntentToUri(intent: Intent?): Uri {
    // Note: implementing this is only required if you plan on catching URL requests.
    // This is an example solution.
    var uriBuilder: Uri.Builder = Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
    if (intent == null) return uriBuilder.build()
    val data = intent.data
    if (data != null && data.path != null) {
      val path = data.path.replace("/", "")
      uriBuilder = uriBuilder.path(path)
    }
    val context = context
    if (context != null) {
      uriBuilder = uriBuilder.authority(context.getPackageName())
    }
    val sliceUri = uriBuilder.build()
    if (!TextUtils.isEmpty(data.path)) {
      presenter.getProperties(sliceUri, data.path.substring(data.path.lastIndexOf("/") + 1), null, null)
    }
    return sliceUri
  }

  @SuppressLint("Slices")
  override fun onBindSlice(sliceUri: Uri): Slice? {
    val response = cacheResponse.get(sliceUri) ?: return null
    var count = 0
    val listBuilder =
    // create parent ListBuilder
            ListBuilder(
                    context,
                    sliceUri,
                    ListBuilder.INFINITY
            )
    val location = sliceUri.path.substring(sliceUri.path.lastIndexOf("/") + 1)
    val header = ListBuilder.HeaderBuilder()
            .setTitle("Best Hotels in $location")
            .setPrimaryAction(SliceAction.create(
                    PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), 0),
                    IconCompat.createWithResource(context, R.drawable.ic_access_time_black_8dp),
                    ListBuilder.ICON_IMAGE,
                    location
            ))

    listBuilder.setHeader(header)
    val grid = GridRowBuilder()
    for (row in 0..response.result.size) {
      val bitmap = cache.get(response.result[row].main_photo_url)
      bitmap?.let {
        val cellB = GridRowBuilder.CellBuilder()
        cellB.addImage(IconCompat.createWithBitmap(it), ListBuilder.LARGE_IMAGE)
        cellB.addText(response.result[row].hotel_name!!)
        cellB.addTitleText("Checkin ${response.result[row].checkin!!.from!!}")
        cellB.contentIntent = PendingIntent.getActivity(context, 0, createDefaultUri(response.result[row].hotel_id!!), 0)
        grid.addCell(cellB)
        ++count
      }
      if (count == 2) break
    }
    listBuilder.addGridRow(grid)
    return listBuilder.build()
  }

  override fun onSliveView(sliceUri: Uri, searchResultUseCase: SearchResultResponse) {
    cacheResponse.put(sliceUri, searchResultUseCase)
    var count = 0
    for (item in 0..searchResultUseCase.result.size) {
      try {
        val theBitmap = Glide.with(context).load(searchResultUseCase.result[item].main_photo_url!!.replace("60", "200")).submit().get().toBitmap()
        cache.put(searchResultUseCase.result[item].main_photo_url, theBitmap)
        if (++count == 2) break
      } catch (e: Exception) {
      }
    }
    context.contentResolver.notifyChange(sliceUri, null)
  }

  private fun createDefaultUri(id: Int): Intent {
    val now = LocalDate.now()
    val fmt = DateTimeFormat.forPattern("yyyy-MM-dd")
    val arrival = fmt.print(now.plusDays(1))
    val departure = fmt.print(now.plusDays(2))
    val webpage = Uri.parse("booking://hotel/$id?checkin=$arrival&checkout=$departure")
    return Intent(Intent.ACTION_VIEW, webpage)
  }
}
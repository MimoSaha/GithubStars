package com.bykov.igor.githubstars.presentation.slices

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
import androidx.slice.builders.GridRowBuilder
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction
import com.bykov.igor.githubstars.data.user.model.GithubUser
import com.bykov.igor.githubstars.presentation.ui.users.UsersActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class GithubStarsProvider : SliceProvider(), KodeinAware, SliceView {

  override val kodein: Kodein by lazy { (context as KodeinAware).kodein }

  private val presenter: SliceCategoryPresenter by instance()

  private val cache = LruCache<String, Bitmap?>(15)
  private val cacheResponse = LruCache<Uri, List<GithubUser>>(15)

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
      presenter.getUsers(sliceUri)
    }
    return sliceUri
  }

  @SuppressLint("Slices")
  override fun onBindSlice(sliceUri: Uri): Slice? {
    val response = cacheResponse.get(sliceUri)
    response?.let {
      val listBuilder =
      // create parent ListBuilder
          ListBuilder(
              context,
              sliceUri,
              ListBuilder.INFINITY
          )
      val header = ListBuilder.HeaderBuilder()
          .setTitle("Best contributors")
          .setPrimaryAction(SliceAction.create(
              PendingIntent.getActivity(context, 0, Intent(context, UsersActivity::class.java), 0),
              IconCompat.createWithResource(context, android.R.drawable.ic_delete),
              ListBuilder.ICON_IMAGE,
              "Best contributors"
          ))

      listBuilder.setHeader(header)
      val grid = GridRowBuilder()
      for (user in response) {
        val bitmap = cache.get(user.avatarUrl)
        bitmap?.let {
          val cellB = GridRowBuilder.CellBuilder()
          cellB.addImage(IconCompat.createWithBitmap(it), ListBuilder.LARGE_IMAGE)
          cellB.addText(user.login)
          cellB.contentIntent = PendingIntent.getActivity(context, 0,
              createDefaultUri(user.url), 0)
          grid.addCell(cellB)
        }
      }
      listBuilder.addGridRow(grid)
      return listBuilder.build()
    } ?: presenter.getUsers(sliceUri)
    return null
  }


  override fun renderUsers(sliceUri: Uri, users: List<GithubUser>) {
    cacheResponse.put(sliceUri, users)
    presenter.loadImages(sliceUri, users)
  }

  override fun updateBitmaps(sliceUri: Uri, bitmaps: List<Pair<String, Bitmap>>) {
    for (bitmap in bitmaps) {
      cache.put(bitmap.first, bitmap.second)
    }
    context?.contentResolver?.notifyChange(sliceUri, null)
  }

  private fun createDefaultUri(url: String): Intent {
    val webpage = Uri.parse(url)
    return Intent(Intent.ACTION_VIEW, webpage)
  }
}
package com.bykov.igor.githubstars.domain.images

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.HttpURLConnection
import java.net.URL

class LoadImage {

  fun buildUseCaseObservable(params: String): Bitmap {
    val url = URL(params)
    val connection = url.openConnection() as HttpURLConnection
    connection.doInput = true
    connection.connect()
    return BitmapFactory.decodeStream(connection.inputStream)
  }
}
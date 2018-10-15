package com.bykov.igor.githubstars.untils

import android.app.Application
import com.google.android.gms.security.ProviderInstaller
import timber.log.Timber
import java.lang.Exception

object SSLHandler {

  fun handlerSSL(application: Application) {
    try {
      ProviderInstaller.installIfNeeded(application)
    } catch (e: Exception) {
      Timber.e(e,"Google Play Services not available.")
    }

  }
}
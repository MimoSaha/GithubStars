package com.bykov.igor.githubstars

import android.app.Application
import com.bykov.igor.githubstars.data.di.NetModule
import com.bykov.igor.githubstars.presentation.di.AppModule
import com.bykov.igor.githubstars.presentation.di.DataModule
import com.bykov.igor.githubstars.untils.SSLHandler
import com.google.android.gms.security.ProviderInstaller
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule

class BaseApplication : Application(), KodeinAware {

  override val kodein: Kodein = Kodein.lazy {
    import(AppModule.module)
    import(NetModule.module)
    import(DataModule.module)
    import(androidModule(this@BaseApplication))
  }

  override fun onCreate() {
    super.onCreate()
    SSLHandler.handlerSSL(this)
  }
}
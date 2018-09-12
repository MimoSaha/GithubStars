package com.bykov.igor.githubstars

import android.app.Application
import com.bykov.igor.githubstars.presentation.di.AppModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContext

class BaseApplication : Application(), KodeinAware {

  override val kodein: Kodein = Kodein.lazy {
    import(AppModule.module)
  }

  override fun onCreate() {
    super.onCreate()
    appKodein = kodein
  }


  companion object {
    lateinit var appKodein : Kodein
  }
}
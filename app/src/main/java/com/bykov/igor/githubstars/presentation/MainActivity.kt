package com.bykov.igor.githubstars.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bykov.igor.githubstars.BaseApplication
import com.bykov.igor.githubstars.R
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContainer
import org.kodein.di.LazyKodein
import org.kodein.di.generic.instance
import timber.log.Timber

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    Timber.d(http.toString())
  }
}

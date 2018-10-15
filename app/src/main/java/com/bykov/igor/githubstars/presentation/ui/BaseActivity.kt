package com.bykov.igor.githubstars.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import com.bykov.igor.githubstars.presentation.ui.navigator.Navigator

abstract class BaseActivity : AppCompatActivity() {

  protected val navigator: Navigator by lazy { Navigator(this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter()?.let {
      lifecycle.addObserver(it)
    }
  }

  abstract fun presenter() : LifecycleObserver?
}
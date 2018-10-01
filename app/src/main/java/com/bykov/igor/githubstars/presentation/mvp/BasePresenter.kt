package com.bykov.igor.githubstars.presentation.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.Main

/**
 * Created by work on 11/11/17.
 */

open class BasePresenter<V : MvpView> : LifecycleObserver, CoroutineScope {

  private lateinit var job: Job

  protected lateinit var view: V

  fun bind(view: V) {
    this.view = view
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_START)
  fun onCreate() {
    job = Job()
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  fun destroy() {
    job.cancel()
  }


  override val coroutineContext = job + Dispatchers.Main
}

package com.bykov.igor.githubstars.data.di

import com.bykov.igor.githubstars.data.network.GithubService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object NetModule {

  val module = Kodein.Module("NetModule") {


    bind<Retrofit>() with singleton {
      Retrofit.Builder()
          .baseUrl("https://github.com/")
          .addCallAdapterFactory(CoroutineCallAdapterFactory())
          .addConverterFactory(GsonConverterFactory.create())
          .build()
    }

    bind<GithubService>() with singleton {
      instance<Retrofit>().create(GithubService::class.java)
    }


    bind<HttpLoggingInterceptor>() with singleton {
      val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
        Timber.tag("OkHttp").d(it)
      })
      logging.level = HttpLoggingInterceptor.Level.BODY
      logging
    }


  }
}
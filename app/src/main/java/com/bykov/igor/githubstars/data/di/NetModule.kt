package com.bykov.igor.githubstars.data.di

import com.bykov.igor.githubstars.data.network.GithubService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetModule {

  val module = Kodein.Module("NetModule") {

    bind<OkHttpClient>() with singleton {
      OkHttpClient.Builder()
          .addInterceptor(instance<HttpLoggingInterceptor>())
          .build()
    }

    bind<Retrofit>() with singleton {
      Retrofit.Builder()
          .baseUrl("https://api.github.com/")
          .addCallAdapterFactory(CoroutineCallAdapterFactory())
          .addConverterFactory(GsonConverterFactory.create())
          .client(instance())
          .build()
    }

    bind<GithubService>() with singleton {
      instance<Retrofit>().create(GithubService::class.java)
    }

    bind<HttpLoggingInterceptor>() with singleton {
      val logging = HttpLoggingInterceptor()
      logging.level = HttpLoggingInterceptor.Level.BODY
      logging
    }
  }
}
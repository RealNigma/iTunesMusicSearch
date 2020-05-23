package com.realnigma.itunesmusicsearch

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MusicAPIModule {

    @Provides
    fun provideApi() : ITunesAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ITunesAPI::class.java)
    }

    @Provides
    fun provideMusicRepository() : MusicRepository {
        return MusicRepository()
    }

    companion object {
        private const val BASE_URL = "https://itunes.apple.com/"
    }
}
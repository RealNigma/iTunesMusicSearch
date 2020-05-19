package com.realnigma.itunesmusicsearch

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MusicAPIModule {

    @Provides
    @Singleton
    fun provideApi() : ITunesAPI {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ITunesAPI::class.java)

    }

    companion object {
        private const val BASE_URL = "https://itunes.apple.com/"
    }
}
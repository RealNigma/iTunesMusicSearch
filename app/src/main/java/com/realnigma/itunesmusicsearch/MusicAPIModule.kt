package com.realnigma.itunesmusicsearch

import retrofit2.Retrofit

class MusicAPIModule {

    fun provideApi() : iTunesAPI {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
            .create(iTunesAPI::class.java)

    }

    companion object {
        private const val BASE_URL = "https://itunes.apple.com/"
    }
}
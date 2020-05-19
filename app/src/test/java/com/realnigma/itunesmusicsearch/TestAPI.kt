package com.realnigma.itunesmusicsearch

import org.junit.Assert.assertTrue
import org.junit.Test

class TestAPI {

    @Test
    fun testAPI() {
        val api  = MusicAPIModule()
        val albumResponse = api.provideApi().searchAlbum("recovery").execute()
        val music = albumResponse.body()
        val collectionId : Int? = music?.result!![0].collectionId
        val songResponse = api.provideApi().lookupAllTracksFromAlbum(collectionId).execute()
        assertTrue(albumResponse.isSuccessful)
        assertTrue(songResponse.isSuccessful)
    }

    @Test
    fun testRepository() {
        //val repository = MusicRepository(musicAPI = ITunesAPI)
    }
}
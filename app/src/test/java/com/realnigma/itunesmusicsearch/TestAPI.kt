package com.realnigma.itunesmusicsearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TestAPI {

    @get: Rule
    var rule = InstantTaskExecutorRule()

    @InjectMocks
    lateinit var viewModel: MusicViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testViewModel() {
        viewModel.searchAlbum("eminem")
        assertFalse(viewModel.albumResult.isNullOrEmpty())
    }

}
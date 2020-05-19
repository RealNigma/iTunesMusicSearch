package com.realnigma.itunesmusicsearch

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface MusicAPIComponent {
    fun inject(viewModel: MusicViewModel)
}
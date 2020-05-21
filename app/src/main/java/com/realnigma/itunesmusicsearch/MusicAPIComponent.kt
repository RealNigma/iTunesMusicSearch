package com.realnigma.itunesmusicsearch

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MusicAPIModule::class])
interface MusicAPIComponent {
    fun inject(viewModel: MusicViewModel)
}
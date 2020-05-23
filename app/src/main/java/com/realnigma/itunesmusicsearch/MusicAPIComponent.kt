package com.realnigma.itunesmusicsearch

import dagger.Component
import javax.inject.Singleton

@Component(modules = [MusicAPIModule::class])
interface MusicAPIComponent {

    fun inject(repository: MusicRepository)

    fun inject(viewModel: MusicViewModel)
}
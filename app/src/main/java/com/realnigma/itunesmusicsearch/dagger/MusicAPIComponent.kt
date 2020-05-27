package com.realnigma.itunesmusicsearch.dagger

import com.realnigma.itunesmusicsearch.MusicViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MusicAPIModule::class])
interface MusicAPIComponent {

    fun inject(viewModel: MusicViewModel)
}
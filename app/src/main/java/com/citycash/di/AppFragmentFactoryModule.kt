package com.citycash.di

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppFragmentFactoryModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideAppFragmentFactory(
        viewModelFactory: ViewModelProvider.Factory
    ): FragmentFactory {
        return AppFragmentFactory(
            viewModelFactory
        )
    }
}
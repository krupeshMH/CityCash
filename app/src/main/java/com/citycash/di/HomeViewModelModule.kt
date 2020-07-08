package com.citycash.di

import androidx.lifecycle.ViewModelProvider
import com.citycash.network.GetProductListAPI
import com.citycash.ui.common.HomeViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object HomeViewModelModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideHomeViewModelFactory(api: GetProductListAPI): ViewModelProvider.Factory {
        return HomeViewModelFactory(api = api)
    }

}
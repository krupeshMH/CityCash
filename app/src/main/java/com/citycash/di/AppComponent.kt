package com.citycash.di

import com.citycash.BaseApplication
import com.citycash.MainActivity
import com.citycash.ui.detail.ProductDetailsFragment
import com.citycash.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AppFragmentFactoryModule::class,
        HomeViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {

        fun create(@BindsInstance app: BaseApplication): AppComponent
    }

    fun inject(homeFragment: HomeFragment)

    fun inject(productDetailsFragment: ProductDetailsFragment)

    fun inject(mainActivity: MainActivity)
}
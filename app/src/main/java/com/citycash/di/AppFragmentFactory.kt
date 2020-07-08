package com.citycash.di

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.citycash.ui.detail.ProductDetailsFragment
import com.citycash.ui.home.HomeFragment
import javax.inject.Inject

class AppFragmentFactory
@Inject
constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String) =

        when (className) {

            HomeFragment::class.java.name -> {
                val fragment = HomeFragment(viewModelFactory)
                fragment
            }

            ProductDetailsFragment::class.java.name -> {
                val fragment = ProductDetailsFragment(viewModelFactory)
                fragment
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }
}
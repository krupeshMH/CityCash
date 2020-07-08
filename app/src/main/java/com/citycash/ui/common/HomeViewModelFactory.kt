package com.citycash.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.citycash.network.GetProductListAPI
import com.citycash.ui.home.HomeViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeViewModelFactory
@Inject
constructor(private val api: GetProductListAPI) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            HomeViewModel::class.java -> {
                HomeViewModel(api) as T
            }
            else -> {
                throw IllegalArgumentException("unknown model class $modelClass")
            }
        }
    }
}
package com.citycash.ui.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.citycash.BaseApplication
import com.citycash.di.AppComponent

abstract class BaseFragment
constructor(
    @LayoutRes private val layoutRes: Int
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    abstract fun inject()

    fun getAppComponent(): AppComponent {
        return activity?.run {
            (application as BaseApplication).appComponent
        } ?: throw Exception("AppComponent is null.")
    }

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

}
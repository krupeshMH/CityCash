package com.citycash.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.citycash.R
import com.citycash.model.DataItem
import com.citycash.ui.Resource
import com.citycash.ui.common.BaseFragment
import com.citycash.util.ComparatorA
import com.citycash.util.ComparatorB
import com.citycash.util.ComparatorC
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment
@Inject
constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : BaseFragment(R.layout.fragment_home), HomeAdapter.Interaction,
    SwipeRefreshLayout.OnRefreshListener {

    private lateinit var homeAdapter: HomeAdapter
    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    override fun inject() {
        getAppComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
        subscribeObservers()
        viewModel.callProductListApi()
    }

    private fun setUi() {
        recycler_product_list.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            ))
            homeAdapter = HomeAdapter(this@HomeFragment)
            adapter = homeAdapter
        }

        swipe_refresh.setOnRefreshListener(this)

        radio_group.setOnCheckedChangeListener { _, _ ->
            homeAdapter.clearList()
            viewModel.callProductListApi()
        }
    }

    private fun subscribeObservers() {
        viewModel.listProducts.observe(viewLifecycleOwner, Observer { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    // show progress
                    swipe_refresh.isRefreshing = true
                }

                Resource.Status.SUCCESS -> {
                    // show data
                    if (response.data != null) {
                        swipe_refresh.isRefreshing = false
                        var list = (response.data.data as List<DataItem>)
                        when (radio_group.checkedRadioButtonId) {
                            R.id.radioA -> {
                                val comparatorA = ComparatorA()
                                list = list.sortedWith(comparatorA)
                            }
                            R.id.radioB -> {
                                val comparatorB = ComparatorB()
                                list = list.sortedWith(comparatorB)
                            }
                            R.id.radioC -> {
                                val comparatorC = ComparatorC()
                                list = list.sortedWith(comparatorC)
                            }
                        }

                        homeAdapter.submitList(list)
                    }
                }

                Resource.Status.ERROR -> {
                    // show error
                    swipe_refresh.isRefreshing = false
                    Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    override fun onItemSelected(position: Int, item: DataItem) {
        item.id?.let {
            val bundle = Bundle()
            bundle.putString("Id", it)
            findNavController()
                .navigate(
                    R.id.action_nav_home_to_nav_detail, bundle
                )
        }
    }

    override fun onRefresh() {
        viewModel.callProductListApi()
    }
}
package com.citycash.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.citycash.R
import com.citycash.model.ProductDetailResponse
import com.citycash.ui.Resource
import com.citycash.ui.common.BaseFragment
import com.citycash.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class ProductDetailsFragment
@Inject
constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : BaseFragment(R.layout.fragment_detail) {

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }
    private var prodId = ""

    override fun inject() {
        getAppComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress_bar.visibility = View.VISIBLE
        main_layout.visibility = View.GONE
        arguments?.let {
            prodId = it.getString("Id").toString()
        }
        subscribeObservers()
        viewModel.callProductDetailsApi(prodId)
    }

    private fun subscribeObservers() {
        viewModel.productDetail.observe(viewLifecycleOwner, Observer { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    // show progress
                    main_layout.visibility = View.GONE
                }

                Resource.Status.SUCCESS -> {
                    // show data
                    if (response.data != null) {
                        progress_bar.visibility = View.GONE
                        main_layout.visibility = View.VISIBLE
                        updateUi(response.data)
                    }
                }

                Resource.Status.ERROR -> {
                    // show error
                    main_layout.visibility = View.GONE
                    progress_bar.visibility = View.GONE
                    Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    @SuppressLint("SetTextI18n")
    private fun updateUi(data: ProductDetailResponse) {
        tv_product_name_detail.text = data.name
        tv_product_id.text = data.id
        tv_product_price.text = "₹ "+data.price
        tv_product_size.text = data.size
        context?.let {
            Glide.with(it)
                .load(data.image)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_detail)
        }
    }

}
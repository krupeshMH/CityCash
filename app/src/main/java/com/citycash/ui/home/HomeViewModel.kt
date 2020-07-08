package com.citycash.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.citycash.model.ProductDetailResponse
import com.citycash.model.ProductListResponse
import com.citycash.network.GetProductListAPI
import com.citycash.ui.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel
@Inject
constructor(private val api: GetProductListAPI) : ViewModel() {

    var listProducts: MutableLiveData<Resource<ProductListResponse>> = MutableLiveData()
    var productDetail: MutableLiveData<Resource<ProductDetailResponse>> = MutableLiveData()

    private var searchDisposable: Disposable? = null

    fun callProductListApi() {
        listProducts.value = Resource.loading(null)
        searchDisposable = api.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ searchResponse ->
                if (searchResponse != null) {
                    listProducts.value = Resource.success(searchResponse)
                }
            }, { throwable ->
                listProducts.setValue(
                    Resource.error(
                        throwable.message ?: "Something went wrong!!!",
                        null
                    )
                )

            })
    }

    fun callProductDetailsApi(id: String) {
        productDetail.value = Resource.loading(null)
        searchDisposable = api.getProductDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ searchResponse ->
                if (searchResponse != null) {
                    productDetail.value = Resource.success(searchResponse)
                }
            }, {throwable->
                productDetail.setValue(Resource.error(throwable.message ?:"Something went wrong!!!", null))
            })
    }
}
package com.citycash.network

import com.citycash.model.ProductDetailResponse
import com.citycash.model.ProductListResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface GetProductListAPI {

    @GET("interview/productList")
    fun getProducts(): Flowable<ProductListResponse>

    @GET("interview/productDetail/{product_id}")
    fun getProductDetails(@Path("product_id") id: String): Flowable<ProductDetailResponse>
}
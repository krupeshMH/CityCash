package com.citycash.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductDetailResponse(
	val image: String? = null,
	val sortProps: SortPropsDetail? = null,
	val categoryName: String? = null,
	val categoryId: String? = null,
	val size: String? = null,
	val price: String? = null,
	val qty: String? = null,
	val name: String? = null,
	val description: String? = null,
	val id: String? = null
) : Parcelable

@Parcelize
data class SortPropsDetail(
	val A: Int? = null,
	val B: Int? = null,
	val C: Int? = null
) : Parcelable

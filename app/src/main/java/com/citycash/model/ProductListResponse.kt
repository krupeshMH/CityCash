package com.citycash.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductListResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("sort_props")
	val sortProps: SortProps? = null,

	@field:SerializedName("category_name")
	val categoryName: String? = null,

	@field:SerializedName("category_id")
	val categoryId: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("qty")
	val qty: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null
) : Parcelable

@Parcelize
data class SortProps(

	@field:SerializedName("A")
	val A: Int? = null,

	@field:SerializedName("B")
	val B: Int? = null,

	@field:SerializedName("C")
	val C: Int? = null
) : Parcelable

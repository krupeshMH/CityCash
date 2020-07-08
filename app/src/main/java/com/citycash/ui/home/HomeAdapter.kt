package com.citycash.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.citycash.R
import com.citycash.model.DataItem
import kotlinx.android.synthetic.main.product_list_item.view.*

class HomeAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {

        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.product_list_item,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<DataItem>) {
        differ.submitList(list)
    }

    fun clearList() {
        differ.submitList(listOf())
    }

    class HomeViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(item: DataItem) = with(itemView) {

            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            itemView.tv_product_name.text = item.name
            itemView.tv_qty.text = item.qty
            itemView.tv_product_qty.text = "QTY: " + item.qty
            itemView.tv_product_price.text = "₹ " + item.price

            Glide.with(itemView.context)
                .load(item.image)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.thumbImageView)
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: DataItem)
    }
}
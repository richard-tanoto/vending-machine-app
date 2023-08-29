package com.richard.vendingmachineapp.feature.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.richard.vendingmachineapp.R
import com.richard.vendingmachineapp.data.model.Item
import com.richard.vendingmachineapp.databinding.ItemItemBinding
import com.richard.vendingmachineapp.util.NumberUtil
import java.text.NumberFormat
import java.util.Locale

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private val listItem = ArrayList<Item>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(list: List<Item>?) {
        if (list == null) return
        listItem.clear()
        listItem.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private var binding: ItemItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(item.imageUrl)
                    .into(ivItem)
                tvName.text = item.name
                tvPrice.text = NumberUtil.convertToRupiah(item.price)
                tvStock.text = String.format(itemView.context.getString(R.string.stock_format), item.stock)
                if (item.stock == 0) {
                    Glide.with(itemView.context)
                        .load(itemView.context.getString(R.string.out_of_stock_url))
                        .into(ivOutOfStock)
                    root.isClickable = false
                    root.isEnabled = false
                }
                root.setOnClickListener { onItemClickCallback.onItemClicked(item) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listItem.size

    interface OnItemClickCallback{
        fun onItemClicked(data: Item)
    }
}